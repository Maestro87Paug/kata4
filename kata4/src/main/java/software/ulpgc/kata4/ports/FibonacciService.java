package software.ulpgc.kata4.ports;

import software.ulpgc.kata4.domain.FibonacciSeries;
import java.io.IOException;

public interface FibonacciService {
    FibonacciSeries getSeries(int n) throws IOException, InterruptedException;
}
