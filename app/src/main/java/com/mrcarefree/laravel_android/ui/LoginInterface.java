package com.mrcarefree.laravel_android.ui;

public interface LoginInterface {
    interface View{
        void onLoadLogin(boolean loading);
        void onResultLogin();
        void onErrorLogin();

        void showMessage(String msg);

        String getEmail();
        String getPassword();
    }
    interface Presenter {
        void postLogin(String email, String password);
    }
}
