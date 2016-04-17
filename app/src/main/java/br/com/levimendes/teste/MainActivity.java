package br.com.levimendes.teste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import java.util.ArrayList;
import java.util.Arrays;
import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.mvp.MainActivityPresenter;
import br.com.levimendes.teste.mvp.MainActivityView;
import br.com.levimendes.teste.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    CallbackManager callbackManager;
    MainActivityPresenter presenter;
    @Bind(R.id.login_button) LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginButton.setReadPermissions(Arrays.asList("user_friends", "public_profile", "user_posts", "email"));
        callbackManager = CallbackManager.Factory.create();

        presenter = new MainActivityPresenter(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, presenter.facebookCallbackLogin());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showSnack(View view, int idMsg) {
        //SnackUtil.showSnackShort(, );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showToast(int idMsg) {
        ToastUtil.showShort(this, getString(idMsg));
    }

    @Override
    public void launchTimelineActivity(ArrayList<Post> posts) {
        Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
        intent.putExtra("posts", posts);
        startActivity(intent);
    }
}