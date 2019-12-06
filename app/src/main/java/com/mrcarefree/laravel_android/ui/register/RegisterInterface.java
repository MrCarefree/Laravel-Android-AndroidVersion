package com.mrcarefree.laravel_android.ui.register;

import com.mrcarefree.laravel_android.data.model.register.ResponseRegister;

public interface RegisterInterface {
    interface View{
        void onResultRegister(ResponseRegister response);
        void onErrorRegister();
        void onLoadRegister(boolean loading);

        void showMessage(String msg);

        String getEmail();
        String getName();
        String getPassword();
    }
    interface Presenter{
        void postRegister(String email, String name, String password);
    }
}
