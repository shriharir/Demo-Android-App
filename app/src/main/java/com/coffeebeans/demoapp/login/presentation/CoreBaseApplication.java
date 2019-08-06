package com.coffeebeans.demoapp.login.presentation;

import android.app.Application;

import com.coffeebeans.demoapp.common.presentation.dependency.component.ActivityComponent;
import com.coffeebeans.demoapp.common.presentation.dependency.component.ApplicationComponent;
import com.coffeebeans.demoapp.common.presentation.dependency.component.DaggerActivityComponent;
import com.coffeebeans.demoapp.common.presentation.dependency.component.DaggerApplicationComponent;
import com.coffeebeans.demoapp.common.presentation.dependency.component.FragmentInjector;
import com.coffeebeans.demoapp.common.presentation.dependency.module.ActivityModule;
import com.coffeebeans.demoapp.common.presentation.dependency.module.ApplicationModule;
import com.coffeebeans.demoapp.common.presentation.dependency.module.DataModule;

public class CoreBaseApplication extends Application {

    protected ApplicationComponent applicationComponent;
    protected ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();

        this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public FragmentInjector getFragmentInjector() {
        return DaggerActivityComponent.builder()
                .applicationComponent(this.applicationComponent).build();
    }

    public ActivityComponent getActivityComponent() {
        return this.activityComponent;
    }

}
