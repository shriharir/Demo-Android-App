package com.coffeebeans.demoapp.common.presentation.presenter;

import com.coffeebeans.demoapp.common.presentation.view.BaseView;

public interface Presenter {

    void initWithView(BaseView view);

    void destroy();
}
