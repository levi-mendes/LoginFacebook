package br.com.levimendes.teste.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import br.com.levimendes.teste.mvp.view.BasicView;
import butterknife.ButterKnife;
import static br.com.levimendes.teste.util.SnackUtil.showSnackLong;
import static br.com.levimendes.teste.util.ToastUtil.showLong;

/**
 * Created by 809778 on 05/07/2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements BasicView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        ButterKnife.bind(this);
    }

    @Override
    public void showSnack(View view, int idMsg) {
        showSnackLong(getCurrentFocus(), getString(idMsg));
    }

    @Override
    public void showToast(int idMsg) {
        showLong(this, getString(idMsg));
    }

    @Override
    public void showToast(String msg) {
        showLong(this, msg);
    }
}
