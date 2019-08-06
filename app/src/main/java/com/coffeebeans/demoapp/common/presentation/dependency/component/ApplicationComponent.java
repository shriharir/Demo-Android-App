package com.coffeebeans.demoapp.common.presentation.dependency.component;

import android.content.Context;

import com.coffeebeans.demoapp.common.data.net.RestApi;
import com.coffeebeans.demoapp.common.domain.executor.PostExecutionThread;
import com.coffeebeans.demoapp.common.domain.executor.ThreadExecutor;
import com.coffeebeans.demoapp.common.presentation.dependency.module.ApplicationModule;
import com.coffeebeans.demoapp.common.presentation.dependency.module.DataModule;
import com.coffeebeans.demoapp.login.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    RestApi restApi();

    UserRepository userRepository();
}
