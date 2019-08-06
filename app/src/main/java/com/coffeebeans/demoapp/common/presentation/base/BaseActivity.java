package com.coffeebeans.demoapp.common.presentation.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.coffeebeans.demoapp.common.presentation.dependency.component.FragmentInjector;
import com.coffeebeans.demoapp.login.presentation.CoreBaseApplication;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private FragmentInjector fragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
    }

    public FragmentInjector getFragmentInjector() {
        return this.fragmentInjector;
    }


    private void initializeActivityComponent() {
        if (this.fragmentInjector == null) {
            this.fragmentInjector = ((CoreBaseApplication) getApplication()).getFragmentInjector();
        }
    }

    public Context context() {
        return getApplicationContext();
    }

    public void showLoader() {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage("Loading.....");
            this.progressDialog.show();
        }
    }

    public void hideLoader() {
        if (this.progressDialog != null) this.progressDialog.dismiss();
        this.progressDialog = null;
    }
}
