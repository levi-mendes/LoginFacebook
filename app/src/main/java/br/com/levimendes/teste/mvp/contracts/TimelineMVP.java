package br.com.levimendes.teste.mvp.contracts;

import com.facebook.AccessToken;
import java.util.ArrayList;
import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.bean.User;

/**
 * Created by Levi on 17/04/2016.
 */
public interface TimelineMVP {

    interface View {
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
        void finalizar();
        void callTelaAmigos();
    }

    interface Presenter {
        void onBackPressed();
        void init();
        void menuNavigation(int idItem);
    }
}