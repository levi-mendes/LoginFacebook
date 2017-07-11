package br.com.levimendes.teste.mvp.presenter;

import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.mvp.view.MainActivityMVP;

/**
 * Created by Levi on 16/04/2016.
 */
public class MainPresenter implements MainActivityMVP.Presenter {

    private MainActivityMVP.View mView;

    public MainPresenter(MainActivityMVP.View view) {
        mView = view;
    }

    private List<Post> graphResponseToPostList(GraphResponse response) {
        if (response != null) {

            try {
                JSONArray jsonObject = response.getJSONObject().getJSONArray("data");

                Gson gson = new Gson();
                List<Post> posts = gson.fromJson(jsonObject.toString(), new TypeToken<List<Post>>(){}.getType());
                return posts;

            } catch (JSONException e) {
                Log.e("onCompleted", e.getMessage(), e);
                mView.showToast(e.getMessage());
            }
        }

        return null;
    }

    private GraphRequest.Callback callback(LoginResult loginResult) {
        GraphRequest.Callback callback = response -> {
                List<Post> posts = graphResponseToPostList(response);
                mView.launchTimelineActivity(posts, loginResult.getAccessToken());
        };

        return callback;
    }

    @Override
    public FacebookCallback<LoginResult> facebookCallbackLogin() {
        FacebookCallback<LoginResult> retorno = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "me/feed", null, HttpMethod.GET, callback(loginResult));
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e("MainPresenter", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("onError", exception.getMessage(), exception);
            }
        };

        return retorno;
    }

    private void teste(AccessToken token) {
        //AccessToken token = AccessToken.getCurrentAccessToken();
        GraphRequest graphRequest = GraphRequest.newMeRequest(token, (jsonObject, graphResponse) -> {
                try {
                    JSONArray jsonArrayFriends = jsonObject.getJSONObject("friendlist").getJSONArray("data");
                    JSONObject friendlistObject = jsonArrayFriends.getJSONObject(0);
                    String frienListID = friendlistObject.getString("id");
                    Log.e("error", frienListID);

                } catch (JSONException e) {
                    Log.e("error", e.getMessage(), e);
                }
        });
        Bundle param = new Bundle();
        param.putString("fields", "friendlist");
        graphRequest.setParameters(param);
        graphRequest.executeAsync();
    }
}