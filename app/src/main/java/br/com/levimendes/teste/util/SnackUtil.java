package br.com.levimendes.teste.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by 429317980 on 23/12/2015.
 */
public class SnackUtil {

    public static void showSnackLong(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public static void showSnackShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }
}
