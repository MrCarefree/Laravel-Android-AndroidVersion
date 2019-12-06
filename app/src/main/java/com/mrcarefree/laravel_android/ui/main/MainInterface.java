package com.mrcarefree.laravel_android.ui.main;

import com.mrcarefree.laravel_android.data.model.student.ResponseStudent;
import com.mrcarefree.laravel_android.data.model.student.delete.ResponseDelete;

public interface MainInterface {
    interface View {
        void onLoadingStudent(boolean loading);
        void onResultStudent(ResponseStudent responseStudent);
        void onErrorStudent();

        void onDelete(String nim);
        void onLoadDelete(boolean loading);
        void onResultDelete(ResponseDelete responseDelete);
        void onErrorDelete();

        void showMessage(String msg);
    }
    interface Presenter{
        void getStudent();
        void deleteStudent(String nim);
    }
}
