package com.coffeebeans.demoapp.common.presentation.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.coffeebeans.demoapp.R;
import com.coffeebeans.demoapp.common.data.net.error.RestApiErrorException;
import com.coffeebeans.demoapp.common.presentation.util.Utility;
import com.coffeebeans.demoapp.common.presentation.view.BaseView;
import com.coffeebeans.demoapp.login.presentation.CoreBaseApplication;
import com.coffeebeans.demoapp.login.presentation.view.activity.LoginActivity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


public abstract class CleanActivity extends BaseActivity implements BaseView {
    protected CoreBaseApplication coreBaseApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coreBaseApplication = ((CoreBaseApplication) this.getApplication());
    }

    @Override
    public void handleError(Throwable error) {
        if (error instanceof RestApiErrorException) {
            String failed_login_message;
            switch (((RestApiErrorException) error).getStatusCode()) {
                case RestApiErrorException.UNAUTHORIZED:
                    failed_login_message = getResources().getString(R.string.error_unauthorized);
                    break;
                case RestApiErrorException.NOT_FOUND:
                    failed_login_message = getResources().getString(R.string.error_not_found);
                    break;
                case RestApiErrorException.FORBIDDEN:
                    failed_login_message = getResources().getString(R.string.error_forbidden);
                    break;
                case RestApiErrorException.BAD_REQUEST:
                    failed_login_message = getResources().getString(R.string.error_bad_request);
                    break;
                default:
                    failed_login_message = error.getMessage();
                    break;
            }
            Utility.showAlertDialogOK(CleanActivity.this, failed_login_message, "Error");
        } else if (error instanceof ConnectException || error instanceof SocketTimeoutException || error instanceof UnknownHostException) {
            Utility.showAlertDialogOK(this, "no internet", "Error");
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void closeAndDisplayLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
    }
}
