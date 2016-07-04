package br.com.levimendes.teste;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import br.com.levimendes.teste.adapter.ListaPostsAdapter;
import br.com.levimendes.teste.bean.Post;
import br.com.levimendes.teste.bean.User;
import br.com.levimendes.teste.mvp.TimelineActivityPresenter;
import br.com.levimendes.teste.mvp.TimelineActivityView;
import br.com.levimendes.teste.util.SnackUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimelineActivity extends AppCompatActivity implements TimelineActivityView,
        NavigationView.OnNavigationItemSelectedListener {

    private TimelineActivityPresenter presenter;

    @Bind(R.id.rvPosts)
    RecyclerView rvPosts;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView nav_view;
    @Bind(R.id.pbProcessamento)
    ProgressBar pbProcessamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        nav_view.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);
        presenter = new TimelineActivityPresenter(this);
        presenter.init();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //vistoriaActivityPresenter.navigate(id);

        return true;
    }

    @Override
    public AccessToken tokenAcesso() {
        return (AccessToken)getIntent().getParcelableExtra("token");
    }

    @Override
    public void carregarDados(User user) {
        View header = nav_view.getHeaderView(0);
        ImageView imageView = (ImageView)header.findViewById(R.id.imageView);
        TextView tvNome = (TextView)header.findViewById(R.id.tvNome);

        tvNome.setText(user.nome);

        Picasso.with(this)
                .load(user.urlPicture)
                //.placeholder(R.drawable.user_placeholder)
                //.error(R.drawable.user_placeholder_error)
                .into(imageView);
    }

    @Override
    public void hideProcessamento() {
        pbProcessamento.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPbProcessamento() {
        pbProcessamento.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.fab)
    void fab(View view) {
        SnackUtil.showSnackShort(view, getString(R.string.adicionar_um_post_na_linha_do_tempo));
    }

    @Override
    public void configurarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void configurarRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvPosts.setLayoutManager(layoutManager);
        rvPosts.setItemAnimator(new DefaultItemAnimator());
        rvPosts.setHasFixedSize(true);
    }

    @Override
    public ArrayList<Post> posts() {
        return (ArrayList<Post>) getIntent().getSerializableExtra("posts");
    }

    @Override
    public void preencherLista(ArrayList<Post> posts) {
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