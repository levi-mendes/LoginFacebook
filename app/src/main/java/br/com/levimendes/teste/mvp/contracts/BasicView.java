package br.com.levimendes.teste.mvp.contracts;

import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by Levi on 17/04/2016.
 */
public interface BasicView {
    //teste
    void showSnack(View view, @StringRes int idMsg);
    void showToast(@StringRes int idMsg);
    void showToast(String msg);
}
