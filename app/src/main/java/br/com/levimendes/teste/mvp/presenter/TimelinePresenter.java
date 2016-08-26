package br.com.levimendes.teste.mvp.presenter;

import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import org.json.JSONException;
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
                sair();
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

        printDados(mView.tokenAcesso());
    }

    private void sair() {
        LoginManager.getInstance().logOut();
        mView.finalizar();
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
            URL profilePic = new URL(String.format("https://graph.facebook.com/%s/urlPicture?type=large", id));
            return profilePic.toString();

        } catch (MalformedURLException e) {
            Log.e("error", e.getMessage(), e);
        }

        return null;
    }

    private User getFacebookData(JSONObject object) {
        //TODO refatorar codigo - Levi Mendes
        User retorno = new User();

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            retorno.urlPicture = urlPicture(id);

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
                retorno.dataNascimento = object.getString("birthday");


            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));


            //return bundle;

        } catch (JSONException e) {
            Log.e("getFacebookData", e.getMessage(), e);
        }

        return retorno;
    }
}
