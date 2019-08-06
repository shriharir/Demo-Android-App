package com.coffeebeans.demoapp.login.presentation.view;

import com.coffeebeans.demoapp.common.presentation.view.BaseView;
import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;

public interface LoginView extends BaseView {
    void showHomepage(AuthResponse user);
}
