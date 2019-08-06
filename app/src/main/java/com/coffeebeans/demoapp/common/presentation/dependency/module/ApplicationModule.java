package com.coffeebeans.demoapp.common.presentation.dependency.module;

import android.content.Context;

import com.coffeebeans.demoapp.common.domain.executor.PostExecutionThread;
import com.coffeebeans.demoapp.common.domain.executor.ThreadExecutor;
import com.coffeebeans.demoapp.common.presentation.executor.JobExecutor;
import com.coffeebeans.demoapp.common.presentation.executor.UIThread;
import com.coffeebeans.demoapp.login.presentation.CoreBaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final CoreBaseApplication baseApplication;

    public ApplicationModule(CoreBaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.baseApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
