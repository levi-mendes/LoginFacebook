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

    public List<Friend> deserialize(String content) {
        Gson gson = new Gson();
        JsonElement jsonElementRoot = gson.fromJson(content, JsonElement.class);
        List<Friend> retorno = new ArrayList<>();

        JsonObject root = jsonElementRoot.getAsJsonObject();
        JsonArray array = root.getAsJsonArray("data");

        for (int cont = 0; cont < array.size(); cont++) {
            JsonElement jsonElement = array.get(cont);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String id             = jsonObject.get("id").toString().replaceAll("\"", "");
            String name           = jsonObject.get("name").toString().replaceAll("\"", "");
            String urlPicture     = urlPicture(jsonObject);

            Friend friend = new Friend();

            friend.id         = id;
            friend.name       = name;
            friend.urlPicture = urlPicture;

            retorno.add(friend);
        }

        return retorno;
    }

    private String urlPicture(JsonObject jsonObject) {
        try {
            JsonObject objPicture =  jsonObject.getAsJsonObject("picture");
            JsonObject objData    = objPicture.getAsJsonObject("data");
            String urlPicture     = objData.get("url").toString().replaceAll("\"", "");

            return urlPicture;

        } catch (Exception e) {
            Log.e("urlPicture", e.getMessage(), e);
        }

        return null;
    }
}