package br.com.levimendes.teste;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
import br.com.levimendes.teste.adapter.ListaPostsAdapter;
import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.mvp.TimelineActivityPresenter;
import br.com.levimendes.teste.mvp.TimelineActivityView;
import br.com.levimendes.teste.util.SnackUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimelineActivity extends AppCompatActivity implements TimelineActivityView {

    private TimelineActivityPresenter presenter;

    @Bind(R.id.rvPosts) RecyclerView rvPosts;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer)  DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        presenter = new TimelineActivityPresenter(this);

        configurarDrawer();
        configurarRecyclerView();
    }

    @OnClick(R.id.fab)
    void fab(View view) {
        SnackUtil.showSnackShort(view, getString(R.string.adicionar_um_post_na_linha_do_tempo));
    }

    private void configurarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configurarRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvPosts.setLayoutManager(layoutManager);
        rvPosts.setItemAnimator(new DefaultItemAnimator());
        rvPosts.setHasFixedSize(true);

        ArrayList<Post> posts = (ArrayList<Post>) getIntent().getSerializableExtra("posts");

        ListaPostsAdapter adapter = new ListaPostsAdapter(posts);
        rvPosts.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean drawerIsOpen() {
        return drawer.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void showSnack(View view, int idMsg) {}

    @Override
    public void showToast(int idMsg) {

    }

    @Override
    public void backPressed() {
        super.onBackPressed();
    }
}