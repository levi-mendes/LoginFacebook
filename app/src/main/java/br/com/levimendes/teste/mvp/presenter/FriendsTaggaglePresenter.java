package br.com.levimendes.teste.mvp.presenter;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import java.util.List;
import br.com.levimendes.teste.bean.Friend;
import br.com.levimendes.teste.deserializer.FriendsDeserializer;
import br.com.levimendes.teste.mvp.view.FriendsTaggableMVP;

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

        AccessToken token = AccessToken.getCurrentAccessToken();

        GraphRequest graphRequest = new GraphRequest(token, TAGGABLE_FRIENDS, null, HttpMethod.GET, callback());
        graphRequest.executeAsync();
    }

    private GraphRequest.Callback callback() {
        return response -> {
            FriendsDeserializer deserializer = new FriendsDeserializer();

            List<Friend> friends = deserializer.deserialize(response.getRawResponse());

            mView.carregarLista(friends);
            mView.hideProgress();
        };
    }
}
