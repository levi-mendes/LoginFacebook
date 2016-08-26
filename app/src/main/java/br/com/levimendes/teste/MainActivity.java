package br.com.levimendes.teste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import java.util.ArrayList;
import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.mvp.presenter.MainActivityPresenter;
import br.com.levimendes.teste.mvp.contracts.MainActivityView;
import br.com.levimendes.teste.util.BaseActivity;
import br.com.levimendes.teste.util.SnackUtil;
import br.com.levimendes.teste.util.ToastUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityView {

    CallbackManager callbackManager;
    MainActivityPresenter presenter;
    @BindView(R.id.login_button) LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
    public void launchTimelineActivity(ArrayList<Post> posts, AccessToken token) {
        Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
        intent.putExtra("posts", posts);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    @Override
    public void showSnack(View view, int idMsg) {
        SnackUtil.showSnackLong(getCurrentFocus(), getString(idMsg));
    }

    @Override
    public void showToast(int idMsg) {
        ToastUtil.showLong(this, getString(idMsg));
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showLong(this, msg);
    }
}