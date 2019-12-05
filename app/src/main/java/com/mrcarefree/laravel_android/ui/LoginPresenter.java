package com.mrcarefree.laravel_android.ui;

import com.mrcarefree.laravel_android.data.model.login.ResponseLogin;
import com.mrcarefree.laravel_android.data.model.register.ResponseRegister;
import com.mrcarefree.laravel_android.data.network.Api;
import com.mrcarefree.laravel_android.data.network.ApiEndPoint;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginInterface.Presenter{
    LoginInterface.View view;
    ApiEndPoint endPoint;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        endPoint = Api.getUrl().create(ApiEndPoint.class);
    }

    @Override
    public void postLogin(String email, String password) {
        view.onLoadLogin(true);
        endPoint.postLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseLogin>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseLogin responseLogin) {
                        view.onLoadLogin(false);
                        view.onResultLogin(responseLogin);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorLogin();
                        view.showMessage(e.getMessage());
                    }
                });
    }
}
