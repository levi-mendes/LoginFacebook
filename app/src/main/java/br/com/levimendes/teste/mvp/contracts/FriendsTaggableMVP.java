package br.com.levimendes.teste.mvp.contracts;

import java.util.List;

import br.com.levimendes.teste.Friend;

/**
 * Created by 809778 on 11/07/2016.
 */
public interface FriendsTaggableMVP {

    interface View {
        void showProgress();
        void hideProgress();
        void carregarLista(List<Friend> friends);
    }

    interface UserActions {
        void init();
    }
}
