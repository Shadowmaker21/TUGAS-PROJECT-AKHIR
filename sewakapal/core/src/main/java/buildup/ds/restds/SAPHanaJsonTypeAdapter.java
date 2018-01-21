package buildup.ds.restds;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class SAPHanaJsonTypeAdapter<T> implements GenericJsonTypeAdapter<T, List<T>> {
    private Class<T> item;
    private List<String> dateFields;

	public SAPHanaJsonTypeAdapter(Class<T> item) {
		this(item, Collections.EMPTY_LIST);
    }
	
    public SAPHanaJsonTypeAdapter(Class<T> item, List<String> dateFields) {
        this.item = item;
        this.dateFields = dateFields;
    }

    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        List<T> items = new ArrayList<>();

        JsonArray jsonParsered = json.getAsJsonObject().get("d").getAsJsonObject().get("results").getAsJsonArray();

        for(JsonElement jsonElement : jsonParsered) {

            if(!dateFields.isEmpty()) {
                String date;
                long msDate;

                for(String dateField : dateFields) {
                    date = jsonElement.getAsJsonObject().get(dateField).getAsString();
                    jsonElement.getAsJsonObject().remove(dateField);
                    msDate = Long.parseLong(date.substring(date.indexOf("(")+1, date.indexOf(")")));
                    jsonElement.getAsJsonObject().addProperty(dateField, msDate);
                }
            }

            items.add(gson.fromJson(jsonElement, item));
        }

        return items;
    }

    @Override
    public Class<T> getItem() {
        return item;
    }
}