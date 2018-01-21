package buildup.injectors;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;

import java.net.URL;
import java.util.Date;
import java.util.List;

import buildup.ds.restds.GenericJsonTypeAdapter;
import buildup.gson.DateJsonTypeAdapter;
import buildup.gson.DecimalJsonTypeAdapter;
import buildup.gson.URLJsonTypeAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

public class ConverterInjector {
    private static GenericJsonTypeAdapter genericJsonTypeAdapter;

    public static void setGenericJsonTypeAdapter(GenericJsonTypeAdapter typeAdapter) {
        genericJsonTypeAdapter = typeAdapter;
    }

    public static Converter getConverter() {
        if (genericJsonTypeAdapter != null) {
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)    // field policy
                    .registerTypeAdapter($Gson$Types.newParameterizedTypeWithOwner(
                            null, List.class, genericJsonTypeAdapter.getItem()), genericJsonTypeAdapter) // Generic list
                    .registerTypeAdapter(Double.class, new DecimalJsonTypeAdapter())
                    .registerTypeAdapter(Date.class,
                            new DateJsonTypeAdapter())  // Date conversions for allowed formats
                    .registerTypeAdapter(URL.class, new URLJsonTypeAdapter())
                    .addSerializationExclusionStrategy(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            // disable rest _id (most times are mongo attributes) serialization
                            // this could slow down serialization,
                            // but luckily gson is caching the result
                            SerializedName annotation = f.getAnnotation(SerializedName.class);
                            return annotation != null && annotation.value().equals("_id");
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();

            return new GsonConverter(gson);
        }
        throw new IllegalStateException("You must set the type adapter first");
    }
}
