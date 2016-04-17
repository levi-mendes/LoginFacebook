package br.com.levimendes.teste.mvp;

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
import java.util.ArrayList;
import br.com.levimendes.teste.bean.Post;

/**
 * Created by Levi on 16/04/2016.
 */
public class MainActivityPresenter {

    private MainActivityView mMainActivityView;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        mMainActivityView = mainActivityView;
    }

    public FacebookCallback<LoginResult> facebookCallbackLogin() {
        FacebookCallback<LoginResult> retorno = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                /* make the API call */ //locale=br_BR
                GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "me/feed", null, HttpMethod.GET, new GraphRequest.Callback() {

                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        if (response != null) {

                            try {
                                JSONArray jsonObject = response.getJSONObject().getJSONArray("data");

                                Gson gson = new Gson();
                                ArrayList<Post> posts = gson.fromJson(jsonObject.toString(), new TypeToken<ArrayList<Post>>(){}.getType());
                                mMainActivityView.launchTimelineActivity(posts);

                            } catch (JSONException e) {
                                Log.e("onCompleted", e.getMessage(), e);
                            }
                        }
                    }
                });
                //Bundle parameters = new Bundle();
                //parameters.putString("fields", "id,name,email,gender,birthday");
                //graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {}

            @Override
            public void onError(FacebookException exception) {
                Log.e("onError", exception.getMessage(), exception);
            }
        };

        return retorno;
    }
}
