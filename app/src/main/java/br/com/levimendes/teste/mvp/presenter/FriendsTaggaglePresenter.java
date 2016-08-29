package br.com.levimendes.teste.mvp.presenter;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.List;
import br.com.levimendes.teste.bean.Friend;
import br.com.levimendes.teste.deserializer.FriendsDeserializer;
import br.com.levimendes.teste.mvp.contracts.FriendsTaggableMVP;

/**
 * Created by 809778 on 11/07/2016.
 */
public class FriendsTaggaglePresenter implements FriendsTaggableMVP.Presenter {

    private FriendsTaggableMVP.View mView;
    private static final String TAGGABLE_FRIENDS = "/me/taggable_friends";

    public FriendsTaggaglePresenter(FriendsTaggableMVP.View view) {
        mView = view;
    }

    @Override
    public void init() {
        mView.showProgress();

        GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(),
                TAGGABLE_FRIENDS, null, HttpMethod.GET, callback());

        graphRequest.executeAsync();
        //graphRequest();
    }

    /*
    private void graphRequest() {
        GraphRequest graphRequest = new GraphRequest();

        graphRequest.setAccessToken(AccessToken.getCurrentAccessToken());
        graphRequest.setGraphPath(TAGGABLE_FRIENDS);
        graphRequest.setParameters(null);
        graphRequest.setHttpMethod(HttpMethod.GET);
        graphRequest.setCallback(callback());

        graphRequest.executeAsync();
    }
    */

    private GraphRequest.Callback callback() {
        return response -> {
            FriendsDeserializer deserializer = new FriendsDeserializer();

            List<Friend> friends = deserializer.deserialize(response.getRawResponse());

            mView.carregarLista(friends);
            mView.hideProgress();
        };
    }
}
