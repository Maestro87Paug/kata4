package software.ulpgc.kata4.usecases;

import software.ulpgc.kata4.UseCase;
import software.ulpgc.kata4.domain.FibonacciSeries;
import software.ulpgc.kata4.ports.SeriesSerializer;

public class FibonacciCalculationUseCase implements UseCase {
    private final SeriesSerializer serializer;

    public FibonacciCalculationUseCase(SeriesSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public String execute(String value) {
        return serializer.serialize(FibonacciSeries.calculate(deserialize(value)));
    }

    private int deserialize(String value) {
        return Integer.parseInt(value);
    }
}
