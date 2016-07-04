package br.com.levimendes.teste.mvp;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.levimendes.teste.bean.User;

/**
 * Created by Levi on 17/04/2016.
 */
public class TimelineActivityPresenter {

    private TimelineActivityView mView;

    public TimelineActivityPresenter(TimelineActivityView timelineActivityView) {
        mView = timelineActivityView;
    }

    public void onBackPressed() {
        if (mView.drawerIsOpen()) {
            mView.closeDrawer();
            return;
        }

        mView.backPressed();
    }

    public void init() {
        mView.configurarDrawer();
        mView.configurarRecyclerView();
        mView.preencherLista(mView.posts());

        //User user = new User();
        //user.urlPicture = "https://graph.facebook.com/225338341159495/picture?type=large";

        printDados(mView.tokenAcesso());
    }

    private void printDados(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.e("LoginActivity", response.toString());
                // Get facebook data from login
                User user = getFacebookData(object);
                mView.carregarDados(user);
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();

    }

    private User getFacebookData(JSONObject object) {
        User retorno = new User();

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                retorno.urlPicture = profile_pic.toString();

            } catch (MalformedURLException e) {
                Log.e("error", e.getMessage(), e);
                //return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name")) {
                bundle.putString("first_name", object.getString("first_name"));
                retorno.nome = object.getString("first_name");
            }

            if (object.has("last_name")) {
                bundle.putString("last_name", object.getString("last_name"));
                Log.e("", object.getString("last_name"));
            }

            if (object.has("email")) {
                bundle.putString("email", object.getString("email"));
                retorno.email = object.getString("email");

            }

            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));


            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));


            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));


            //return bundle;

        } catch (JSONException e) {
            Log.e("getFacebookData", e.getMessage(), e);
        }

        return retorno;
    }
}
