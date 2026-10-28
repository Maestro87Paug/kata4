package software.ulpgc.kata4.adapters.endpoints;


import io.javalin.http.Context;
import software.ulpgc.kata4.adapters.EndPoint;

public class HolaEndPoint implements EndPoint {
    private final Context context;

    private HolaEndPoint(Context context) {
        this.context = context;
    }

    public static EndPoint use(Context context) {
        return new HolaEndPoint(context);
    }

    @Override
    public void execute() {
        context.result("Hola " + context.pathParam("name") + "!!!");
    }
}
