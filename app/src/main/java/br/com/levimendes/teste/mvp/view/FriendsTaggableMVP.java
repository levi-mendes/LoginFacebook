package br.com.levimendes.teste.mvp.view;

import java.util.List;

import br.com.levimendes.teste.bean.Friend;

/**
 * Created by 809778 on 11/07/2016.
 */
public interface FriendsTaggableMVP {

    interface View {
        void showProgress();
        void hideProgress();
        void carregarLista(List<Friend> friends);
    }

    interface Presenter {
        void init();
    }
}
