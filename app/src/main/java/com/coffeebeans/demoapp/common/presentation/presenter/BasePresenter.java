package com.coffeebeans.demoapp.common.presentation.presenter;

import com.coffeebeans.demoapp.common.domain.interactor.UseCase;
import com.coffeebeans.demoapp.common.presentation.view.BaseView;

import io.reactivex.observers.DisposableObserver;

public class BasePresenter implements Presenter {

    private BaseView view;
    private UseCase useCase0;

    public BasePresenter(UseCase useCase0) {
        this.useCase0 = useCase0;
    }

    @Override
    public void initWithView(BaseView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        if (this.useCase0 != null) this.useCase0.unsubscribe();
        this.view = null;
    }

    public void showLoader() {
        this.view.showLoader();
    }

    public void hideLoader() {
        this.view.hideLoader();
    }

    private void handleError(Throwable error) {
        this.view.handleError(error);
    }


    public class BaseSubscriber<T> extends DisposableObserver<T> {
        @Override
        public void onComplete() {
            BasePresenter.this.hideLoader();
        }

        @Override
        public void onError(Throwable e) {
            BasePresenter.this.hideLoader();
            BasePresenter.this.handleError(e);
        }

        @Override
        public void onNext(T t) {
            BasePresenter.this.showLoader();
        }
    }
}
