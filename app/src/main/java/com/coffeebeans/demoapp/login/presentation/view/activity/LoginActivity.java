package com.coffeebeans.demoapp.login.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeebeans.demoapp.R;
import com.coffeebeans.demoapp.common.data.net.error.RestApiErrorException;
import com.coffeebeans.demoapp.common.presentation.base.CleanActivity;
import com.coffeebeans.demoapp.common.presentation.util.Utility;
import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;
import com.coffeebeans.demoapp.login.presentation.CoreBaseApplication;
import com.coffeebeans.demoapp.login.presentation.presenter.LoginPresenter;
import com.coffeebeans.demoapp.login.presentation.view.LoginView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.coffeebeans.demoapp.common.presentation.util.Utility.showAlertDialogOKCancelAction;

public class LoginActivity extends CleanActivity implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.email)
    AutoCompleteTextView userNameEditText;
    @BindView(R.id.password)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CoreBaseApplication) getApplicationContext()).getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        this.loginPresenter.initWithView(this);
    }

    @Override
    public void showHomepage(AuthResponse authResponse) {

        if (authResponse != null) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
            this.finish();
        } else closeAndDisplayLogin();
    }

    @Override
    public void onBackPressed() {
        showAlertDialogOKCancelAction(LoginActivity.this,
                getString(R.string.title_exit),
                getString(R.string.msg_exit),
                getString(R.string.dialog_yes),
                getString(R.string.dialog_no),
                (dialogInterface, i) -> LoginActivity.super.onBackPressed());
    }

    @Override
    public void handleError(Throwable error) {
        if (error instanceof RestApiErrorException) {
            String failed_login_message;
            switch (((RestApiErrorException) error).getStatusCode()) {
                case RestApiErrorException.UNAUTHORIZED:
                    failed_login_message = getResources().getString(R.string.error_login_unauthorized);
                    break;
                case RestApiErrorException.FORBIDDEN:
                    failed_login_message = getResources().getString(R.string.error_login_forbidden);
                    break;
                case RestApiErrorException.USER_LOCKED:
                    failed_login_message = getResources().getString(R.string.error_user_locked);
                    break;
                default:
                    failed_login_message = getResources().getString(R.string.error_login);
                    break;
            }
            Utility.showAlertDialogOK(LoginActivity.this, failed_login_message, "Error");
        } else if (error instanceof ConnectException || error instanceof SocketTimeoutException || error instanceof UnknownHostException) {
            Utility.showAlertDialogOK(this, getString(R.string.error_login), getString(R.string.error_login));
        } else Toast.makeText(context(), getResources().getString(R.string.error_login),
                Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.email_sign_in_button)
    public void loginButtonPressed() {

        String username = userNameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        boolean isValidLoginFields = true;

        if (username.isEmpty()) {
            userNameEditText.setError("Enter a valid username");
            isValidLoginFields = false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Enter a valid password");
            isValidLoginFields = false;
        }
        if (isValidLoginFields)
            this.loginPresenter.loginUser(username, password);
        else loginPresenter.reinitializeView();
    }
}