package br.com.levimendes.teste.mvp;

import com.facebook.AccessToken;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.List;

import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.bean.User;

/**
 * Created by Levi on 17/04/2016.
 */
public interface TimelineActivityView extends BasicView {

    boolean drawerIsOpen();
    void openDrawer();
    void closeDrawer();
    void backPressed();
    void configurarDrawer();
    void configurarRecyclerView();
    void preencherLista(ArrayList<Post> posts);
    void carregarDados(User user);
    ArrayList<Post> posts();
    void showPbProcessamento();
    void hideProcessamento();
    AccessToken tokenAcesso();
}
