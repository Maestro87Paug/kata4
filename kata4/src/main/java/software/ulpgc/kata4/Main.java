package software.ulpgc.kata4;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import software.ulpgc.kata4.adapters.JsonSeriesSerializer;
import software.ulpgc.kata4.adapters.endpoints.FibonacciEndPoint;
import software.ulpgc.kata4.adapters.endpoints.HolaEndPoint;
import software.ulpgc.kata4.usecases.FibonacciCalculationUseCase;

public class Main {
    public static void main(String[] args) {
        Javalin.create(c -> create(c))
                .start(8081);

    }

    private static void create(JavalinConfig config) {
        config.routes.get("/hola/{name}", context -> HolaEndPoint.use(context).execute());
        config.routes.get("/fibonacci", context -> FibonacciEndPoint.use(new FibonacciCalculationUseCase(new JsonSeriesSerializer()), context).execute());
    }
}
