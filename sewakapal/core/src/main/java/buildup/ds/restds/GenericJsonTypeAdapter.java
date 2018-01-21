package buildup.ds.restds;

import com.google.gson.JsonDeserializer;

public interface GenericJsonTypeAdapter<T, R> extends JsonDeserializer<R> {
    Class<T> getItem();
}
