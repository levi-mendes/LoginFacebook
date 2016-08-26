package br.com.levimendes.teste.mvp.presenter;

import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import br.com.levimendes.teste.mvp.contracts.FriendsTaggableMVP;

/**
 * Created by 809778 on 11/07/2016.
 */
public class FriendsTaggaglePresenter implements FriendsTaggableMVP.UserActions {

    FriendsTaggableMVP.View mView;
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
    }

    private GraphRequest.Callback callback() {
        return response -> {
                Log.e("getFriendsDat",""+ response);

                String res      = response.getRawResponse();

                Log.e("onCompleted", res);
                //List<Friend> friends =

                //mView.carregarLista(friends);
                mView.hideProgress();
        };
    }
}
