package br.com.levimendes.teste.mvp.contracts;

import com.facebook.AccessToken;
import java.util.ArrayList;
import br.com.levimendes.teste.bean.Post;

/**
 * Created by Levi on 16/04/2016.
 */
public interface MainActivityView extends BasicView {

    void launchTimelineActivity(ArrayList<Post> posts, AccessToken token);
}
