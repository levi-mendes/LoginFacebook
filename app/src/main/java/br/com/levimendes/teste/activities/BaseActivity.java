package br.com.levimendes.teste.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import br.com.levimendes.teste.mvp.view.BasicView;
import static br.com.levimendes.teste.util.SnackUtil.showSnackLong;
import static br.com.levimendes.teste.util.ToastUtil.showLong;

/**
 * Created by 809778 on 05/07/2016.
 */
public class BaseActivity extends AppCompatActivity implements BasicView {

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
