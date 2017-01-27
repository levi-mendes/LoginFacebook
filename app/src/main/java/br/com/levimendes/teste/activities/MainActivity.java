package br.com.levimendes.teste.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;
import java.util.ArrayList;
import java.util.List;

import br.com.levimendes.teste.R;
import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.mvp.presenter.MainPresenter;
import br.com.levimendes.teste.mvp.view.MainActivityMVP;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityMVP.View {

    CallbackManager callbackManager;
    MainPresenter presenter;
    @BindView(R.id.login_button) LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();
        presenter = new MainPresenter(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, presenter.facebookCallbackLogin());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void launchTimelineActivity(List<Post> posts, AccessToken token) {
        Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
        intent.putExtra("posts", (ArrayList)posts);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();

        if(profile != null){
            Log.e("name",     profile.getFirstName());
            Log.e("surname",  profile.getLastName());
            Log.e("imageUrl", profile.getProfilePictureUri(200,200).toString());
        }
    }

}