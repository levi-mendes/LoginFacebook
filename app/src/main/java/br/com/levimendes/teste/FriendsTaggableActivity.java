package br.com.levimendes.teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import br.com.levimendes.teste.adapter.ListaFriendsAdapter;
import br.com.levimendes.teste.mvp.contracts.FriendsTaggableMVP;
import br.com.levimendes.teste.mvp.presenter.FriendsTaggaglePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsTaggableActivity extends AppCompatActivity implements FriendsTaggableMVP.View {

    @BindView(R.id.pbProcessamento)
    ProgressBar pbProcessamento;
    @BindView(R.id.rvFriends)
    RecyclerView rvFriends;

    private FriendsTaggaglePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_taggable);
        ButterKnife.bind(this);

        configurarRecyclerView();

        presenter = new FriendsTaggaglePresenter(this);
        presenter.init();
    }

    private void configurarRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvFriends.setLayoutManager(layoutManager);
        rvFriends.setItemAnimator(new DefaultItemAnimator());
        rvFriends.setHasFixedSize(true);
    }

    @Override
    public void showProgress() {
        pbProcessamento.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProcessamento.setVisibility(View.INVISIBLE);
    }

    @Override
    public void carregarLista(List<Friend> friends) {
        ListaFriendsAdapter adapter = new ListaFriendsAdapter(friends);
        rvFriends.setAdapter(adapter);
    }
}
