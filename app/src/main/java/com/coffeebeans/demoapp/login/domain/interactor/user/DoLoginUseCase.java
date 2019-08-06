package com.coffeebeans.demoapp.login.domain.interactor.user;

import com.coffeebeans.demoapp.common.domain.executor.PostExecutionThread;
import com.coffeebeans.demoapp.common.domain.executor.ThreadExecutor;
import com.coffeebeans.demoapp.common.domain.interactor.UseCase;
import com.coffeebeans.demoapp.login.domain.live.entity.AuthResponse;
import com.coffeebeans.demoapp.login.domain.live.entity.UserEntity;
import com.coffeebeans.demoapp.login.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoLoginUseCase extends UseCase<AuthResponse> {

    private UserRepository userRepository;

    private UserEntity user;

    @Inject
    DoLoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                   UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    public void setParams(UserEntity user) {
        this.user = user;
    }

    @Override
    protected Observable<AuthResponse> buildUseCaseObservable() {
        return this.userRepository.loginUser(this.user);
    }
}
