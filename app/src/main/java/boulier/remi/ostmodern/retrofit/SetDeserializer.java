package boulier.remi.ostmodern.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class SetDeserializer implements JsonDeserializer<ArrayList<Set>> {
    @Override
    public ArrayList<Set> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject results = json.getAsJsonObject();

        return new Gson().fromJson(results.get("objects"), typeOfT);
    }
}
