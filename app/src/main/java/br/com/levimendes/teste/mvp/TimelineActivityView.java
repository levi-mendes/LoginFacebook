package br.com.levimendes.teste.mvp;

import java.util.List;

import br.com.levimendes.teste.bean.Post;

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
    void preencherLista();
}
