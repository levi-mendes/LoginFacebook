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
                //Bundle bundle = new Bundle();
                //graphRequest.setParameters();
                graphRequest.executeAsync();

                /*
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("LoginActivity", response.toString());

                                // Application code
                                try {
                                    //String publish_actions = object.getString("publish_actions");
                                    //Log.e("publish_actions", publish_actions);
                                    String user_friends = object.getString("user_friends");
                                    String public_profile = object.getString("public_profile"); // 01/31/1980 format
                                    Log.e("onCompleted", "user_friends: " + user_friends + "\npublic_profile: " + public_profile);

                                } catch (JSONException e) {
                                    Log.e("onCompleted", e.getMessage(), e);
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();*/
            }

            @Override
            public void onCancel() {
                Log.e("onCancel", "Operação cancelada");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("onError", exception.getMessage(), exception);
            }
        };

        return retorno;
    }
}
