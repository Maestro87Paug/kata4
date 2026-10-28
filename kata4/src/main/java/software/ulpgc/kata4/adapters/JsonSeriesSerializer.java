package software.ulpgc.kata4.adapters;

import com.google.gson.Gson;
import software.ulpgc.kata4.ports.SeriesSerializer;

public class JsonSeriesSerializer implements SeriesSerializer {
    @Override
    public String serialize(int[] series) {
        return new Gson().toJson(series);
    }
}
