package com.mrcarefree.laravel_android.ui;

import com.mrcarefree.laravel_android.data.model.register.ResponseRegister;
import com.mrcarefree.laravel_android.data.network.Api;
import com.mrcarefree.laravel_android.data.network.ApiEndPoint;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterInterface.Presenter{
    RegisterInterface.View view;
    ApiEndPoint endPoint;

    public RegisterPresenter(RegisterInterface.View view) {
        this.view = view;
        endPoint = Api.getUrl().create(ApiEndPoint.class);
    }

    @Override
    public void postRegister(String email, String name, String password) {
        view.onLoadRegister(true);
        endPoint.postRegister(email, name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseRegister>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseRegister responseRegister) {
                        view.onLoadRegister(false);
                        view.onResultRegister(responseRegister);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorRegister();
                        view.showMessage(e.getMessage());
                    }
                });
    }
}
