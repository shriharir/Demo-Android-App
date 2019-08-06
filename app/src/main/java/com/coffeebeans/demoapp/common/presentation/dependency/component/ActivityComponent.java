package com.coffeebeans.demoapp.common.presentation.dependency.component;

import com.coffeebeans.demoapp.common.presentation.dependency.ActivityScope;
import com.coffeebeans.demoapp.common.presentation.dependency.module.ActivityModule;
import com.coffeebeans.demoapp.login.presentation.view.activity.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);

}
