package br.com.levimendes.teste.mvp;

/**
 * Created by Levi on 17/04/2016.
 */
public class TimelineActivityPresenter {

    private TimelineActivityView mView;

    public TimelineActivityPresenter(TimelineActivityView timelineActivityView) {
        mView = timelineActivityView;
    }

    public void onBackPressed() {
        if (mView.drawerIsOpen()) {
            mView.closeDrawer();
            return;
        }

        mView.backPressed();
    }

    public void init() {
        mView.configurarDrawer();
        mView.configurarRecyclerView();
        mView.preencherLista();
    }
}
