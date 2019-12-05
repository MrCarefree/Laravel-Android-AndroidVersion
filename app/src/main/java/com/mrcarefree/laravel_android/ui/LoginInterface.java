package com.mrcarefree.laravel_android.ui;

import com.mrcarefree.laravel_android.data.model.login.ResponseLogin;

public interface LoginInterface {
    interface View{
        void onLoadLogin(boolean loading);
        void onResultLogin(ResponseLogin response);
        void onErrorLogin();

        void showMessage(String msg);

        String getEmail();
        String getPassword();
    }
    interface Presenter {
        void postLogin(String email, String password);
    }
}
