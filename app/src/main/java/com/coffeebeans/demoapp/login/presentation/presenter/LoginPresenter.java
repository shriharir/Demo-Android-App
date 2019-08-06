package com.coffeebeans.demoapp.login.presentation.presenter;

import com.coffeebeans.demoapp.common.presentation.dependency.ActivityScope;
import com.coffeebeans.demoapp.common.presentation.presenter.BasePresenter;
import com.coffeebeans.demoapp.common.presentation.presenter.Presenter;
import com.coffeebeans.demoapp.common.presentation.view.BaseView;
import com.coffeebeans.demoapp.login.domain.interactor.user.DoLoginUseCase;
import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;
import com.coffeebeans.demoapp.login.domain.live.entity.UserEntity;
import com.coffeebeans.demoapp.login.presentation.view.LoginView;

import javax.inject.Inject;

@ActivityScope
public class LoginPresenter extends BasePresenter implements Presenter {

    private DoLoginUseCase doLoginUseCase;
    LoginView loginView;

    @Inject
    LoginPresenter(DoLoginUseCase doLoginUseCase) {
        super(doLoginUseCase);
        this.doLoginUseCase = doLoginUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.loginView = (LoginView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.loginView = null;
    }

    public void loginUser(String username, String password) {

        UserEntity user = new UserEntity(username, password);
        this.showLoader();

        this.doLoginUseCase.setParams(user);
        this.doLoginUseCase.execute(new LoginSubscriber());
    }

    public void reinitializeView() {
        this.initWithView(loginView);
    }

    protected class LoginSubscriber extends BaseSubscriber<AuthResponse> {

        @Override
        public void onNext(AuthResponse user) {
            LoginPresenter.this.hideLoader();
            LoginPresenter.this.loginView.showHomepage(user);
        }

    }
}