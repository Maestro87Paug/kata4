package software.ulpgc.kata4.adapters.endpoints;

import io.javalin.http.Context;
import software.ulpgc.kata4.UseCase;
import software.ulpgc.kata4.adapters.EndPoint;

public class FibonacciEndPoint implements EndPoint {
    private final UseCase useCase;
    private final Context context;

    public FibonacciEndPoint(UseCase useCase, Context context) {
        this.useCase = useCase;
        this.context = context;
    }

    public static EndPoint use(UseCase useCase, Context context) {
        return new FibonacciEndPoint(useCase, context);
    }

    @Override
    public void execute() {
        String value = context.queryParam("n");
        try {
            int n = Integer.parseInt(value);
            if (n < 0 || n > 47) {
                context.status(400).result("n debe estar entre 0 y 47");
                return;
            }
            context.contentType("application/json").result(useCase.execute(value));
        } catch (NumberFormatException e) {
            context.status(400).result("n debe ser un número entero");
        }
    }
}
