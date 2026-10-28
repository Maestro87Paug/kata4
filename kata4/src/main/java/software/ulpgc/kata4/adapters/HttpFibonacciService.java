
package software.ulpgc.kata4.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import software.ulpgc.kata4.domain.FibonacciSeries;
import software.ulpgc.kata4.ports.FibonacciService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpFibonacciService implements FibonacciService {
    private final String baseUrl;
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    public HttpFibonacciService(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/")
                ? baseUrl.substring(0, baseUrl.length() - 1)
                : baseUrl;
    }

    @Override
    public FibonacciSeries getSeries(int n) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/fibonacci?n=" + n))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Error HTTP " + response.statusCode());
        }

        return parse(response.body());
    }

    private FibonacciSeries parse(String json) {
        try {
            int[] values = new Gson().fromJson(json, int[].class);

            if (values == null || values.length == 0) {
                throw new IllegalArgumentException(
                        "El servicio ha devuelto una respuesta vacía"
                );
            }

            return new FibonacciSeries(values);

        } catch (JsonParseException e) {
            throw new IllegalArgumentException(
                    "La respuesta no es un JSON válido", e
            );
        }
    }
}
