package br.com.levimendes.teste.mvp;

/**
 * Created by Levi on 17/04/2016.
 */
public class TimelineActivityPresenter {

    private TimelineActivityView mTimelineActivityView;

    public TimelineActivityPresenter(TimelineActivityView timelineActivityView) {
        mTimelineActivityView = timelineActivityView;
    }

    public void onBackPressed() {
        if (mTimelineActivityView.drawerIsOpen()) {
            mTimelineActivityView.closeDrawer();
        } else {
            mTimelineActivityView.backPressed();
        }
    }
}
