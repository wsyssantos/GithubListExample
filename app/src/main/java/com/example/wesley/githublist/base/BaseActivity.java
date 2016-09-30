package com.example.wesley.githublist.base;

import android.support.v7.app.AppCompatActivity;

import com.example.wesley.githublist.customview.DialogSpinner;
import com.example.wesley.githublist.util.DialogUtil;

/**
 * Created by wesley on 9/16/16.
 */
public class BaseActivity extends AppCompatActivity {
    protected void showDialog() {
        DialogSpinner loadingDialog = DialogSpinner.getInstance();
        if(!loadingDialog.isVisible() && !loadingDialog.isInLayout() && !loadingDialog.isAdded() &&!loadingDialog.getIsVisible())
            loadingDialog.show(getFragmentManager(), "loading");
    }

    protected void hideDialog() {
        DialogSpinner loadingDialog = DialogSpinner.getInstance();
        loadingDialog.dismiss();
    }

    protected void showErrorDialog(Throwable e) {
        DialogUtil.showError(this, e.getLocalizedMessage());
    }
}
