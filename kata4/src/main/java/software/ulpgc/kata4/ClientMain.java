package software.ulpgc.kata4;

import software.ulpgc.kata4.adapters.HttpFibonacciService;
import software.ulpgc.kata4.domain.FibonacciSeries;
import software.ulpgc.kata4.ports.FibonacciService;

import java.io.IOException;
import java.util.Arrays;

public class ClientMain {
    private static final String BASE_URL = "http://localhost:8081";

    public static void main(String[] args) {
        try {
            String url;
            if (args.length > 0) url = args[0];
            else url = BASE_URL;

            int n;
            if (args.length > 1) n = Integer.parseInt(args[1]);
            else n = 10;

            FibonacciService service = new HttpFibonacciService(url);
            FibonacciSeries series = service.getSeries(n);
            System.out.println("Serie de Fibonacci (" + n + " términos):");
            System.out.println("Total: " + series.getValues().length + " términos");
        } catch (IOException e) {
            System.out.println("No se puede conectar con el servicio");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La petición se ha interrumpido");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
