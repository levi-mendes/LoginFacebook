package br.com.levimendes.teste.mvp;

import android.view.View;

/**
 * Created by Levi on 17/04/2016.
 */
public interface BasicView {
    //teste
    void showSnack(View view, int idMsg);
    void showToast(int idMsg);
    void showToast(String msg);
}
