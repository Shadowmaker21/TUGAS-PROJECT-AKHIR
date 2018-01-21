package buildup.injectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import buildup.gson.BooleanJsonTypeAdapter;
import buildup.gson.DateJsonTypeAdapter;
import buildup.gson.IntegerJsonTypeAdapter;

public class GsonInjector {

    private static final Gson CLOUDANT_GSON = new GsonBuilder()
            .registerTypeAdapter(Integer.class, new IntegerJsonTypeAdapter())
            .registerTypeAdapter(Date.class, new DateJsonTypeAdapter())
            .registerTypeAdapter(Boolean.class, new BooleanJsonTypeAdapter())
            .create();

    public static Gson cloudantGson() {
        return CLOUDANT_GSON;
    }
}
