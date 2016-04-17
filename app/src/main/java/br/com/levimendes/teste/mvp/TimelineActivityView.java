package br.com.levimendes.teste.mvp;

/**
 * Created by Levi on 17/04/2016.
 */
public interface TimelineActivityView extends BasicView {

    boolean drawerIsOpen();
    void openDrawer();
    void closeDrawer();
    void backPressed();
}
