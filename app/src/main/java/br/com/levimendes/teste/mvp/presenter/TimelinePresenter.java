package br.com.levimendes.teste.mvp.presenter;

import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.google.gson.Gson;
import static java.lang.String.format;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import br.com.levimendes.teste.R;
import br.com.levimendes.teste.bean.User;
import br.com.levimendes.teste.mvp.contracts.TimelineMVP;

/**
 * Created by Levi on 17/04/2016.
 */
public class TimelinePresenter implements TimelineMVP.Presenter {

    private TimelineMVP.View mView;

    public TimelinePresenter(TimelineMVP.View view) {
        mView = view;
    }

    @Override
    public void onBackPressed() {
        if (mView.drawerIsOpen()) {
            mView.closeDrawer();
            return;
        }

        mView.backPressed();
    }

    @Override
    public void menuNavigation(int idItem) {
        switch (idItem) {
            case R.id.itemSair:
                mView.finalizar();
                break;

            case R.id.itemAmigos:
                mView.callTelaAmigos();
                break;
        }
    }

    @Override
    public void init() {
        mView.configurarDrawer();
        mView.configurarRecyclerView();
        mView.preencherLista(mView.posts());

        //printDados(mView.tokenAcesso());
    }

    private GraphRequest.GraphJSONObjectCallback callback() {
        return (object, response) -> {
            // Get facebook data from login
            User user = getFacebookData(object);
            mView.carregarDados(user);
        };
    }

    private void printDados(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, callback());

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();

    }

    private String urlPicture(String id) {
        try {
            URL profilePic = new URL(format("https://graph.facebook.com/%s/picture?type=large", id));
            return profilePic.toString();

        } catch (MalformedURLException e) {
            Log.e("error", e.getMessage(), e);
        }

        return null;
    }

    private User getFacebookData(JSONObject object) {
        Gson gson = new Gson();
        User user = gson.fromJson(object.toString(), User.class);
        user.urlPicture = urlPicture(user.id);

        return user;
    }
}