package br.com.levimendes.teste.mvp.contracts;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

import java.util.List;
import br.com.levimendes.teste.bean.Post;

/**
 * Created by Levi on 16/04/2016.
 */
public interface MainActivityMVP {

    interface View extends BasicView {
        void launchTimelineActivity(List<Post> posts, AccessToken token);
    }

    interface Presenter {
        FacebookCallback<LoginResult> facebookCallbackLogin();
    }
}
