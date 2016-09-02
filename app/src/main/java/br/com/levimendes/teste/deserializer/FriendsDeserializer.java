package br.com.levimendes.teste.deserializer;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import br.com.levimendes.teste.bean.Friend;

/**
 * Created by Levi on 06/11/2015.
 */
public class FriendsDeserializer {

    //comentario de teste levi mendes 02/09/2016
    public List<Friend> deserialize(String content) {
        Gson gson = new Gson();
        JsonElement jsonElementRoot = gson.fromJson(content, JsonElement.class);
        List<Friend> retorno = new ArrayList<>();

        JsonObject root = jsonElementRoot.getAsJsonObject();
        JsonArray array = root.getAsJsonArray("data");

        for (int cont = 0; cont < array.size(); cont++) {
            JsonElement jsonElement = array.get(cont);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String id             = texto(jsonObject.get("id"));
            String name           = texto(jsonObject.get("name"));
            String urlPicture     = urlPicture(jsonObject);

            Friend friend = new Friend();

            friend.id         = id;
            friend.name       = name;
            friend.urlPicture = urlPicture;

            retorno.add(friend);
        }

        return retorno;
    }

    private String texto(JsonElement jsonElement) {
        return jsonElement.toString().replaceAll("\"", "");
    }

    private String urlPicture(JsonObject jsonObject) {
        try {
            JsonObject objPicture =  jsonObject.getAsJsonObject("picture");
            JsonObject objData    = objPicture.getAsJsonObject("data");
            String urlPicture     = texto(objData.get("url"));

            return urlPicture;

        } catch (Exception e) {
            Log.e("urlPicture", e.getMessage(), e);
        }

        return null;
    }
}