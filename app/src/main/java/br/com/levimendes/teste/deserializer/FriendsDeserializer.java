package br.com.levimendes.teste.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import br.com.levimendes.teste.Friend;

/**
 * Created by Levi on 06/11/2015.
 */
public class FriendsDeserializer implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Friend> retorno = new ArrayList<>();

        //retorna todos os objetos
        JsonElement j = json.getAsJsonObject().get("avfms");
        //joga todos os objetos em um array
        JsonArray array = j.getAsJsonArray();

        if (array != null && array.size() > 0) {

            for (int cont = 0; cont < array.size(); cont++) {
                retorno.add(new Gson().fromJson(array.get(cont), Friend.class));
            }
        }

        return retorno;
    }
}
