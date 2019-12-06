package com.mrcarefree.laravel_android.ui.main;

import com.mrcarefree.laravel_android.data.model.student.ResponseStudent;
import com.mrcarefree.laravel_android.data.model.student.delete.ResponseDelete;
import com.mrcarefree.laravel_android.data.network.Api;
import com.mrcarefree.laravel_android.data.network.ApiEndPoint;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainInterface.Presenter {
    MainInterface.View view;
    ApiEndPoint endpoint;

    public MainPresenter(MainInterface.View view) {
        this.view = view;
        endpoint = Api.getUrl().create(ApiEndPoint.class);
    }

    @Override
    public void getStudent() {
        view.onLoadingStudent(true);
        endpoint.getStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseStudent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseStudent responseStudent) {
                        view.onLoadingStudent(false);
                        view.onResultStudent(responseStudent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorStudent();
                        view.showMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void deleteStudent(String nim) {
        view.onLoadDelete(true);
        endpoint.deleteStudent(nim)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseDelete>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseDelete responseDelete) {
                        view.onLoadDelete(false);
                        view.onResultDelete(responseDelete);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorDelete();
                        view.onLoadDelete(false);
                        view.showMessage(e.getMessage());
                    }
                });
    }
}
