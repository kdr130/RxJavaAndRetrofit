package com.kevin.rxjavaandretrofit.Service;

import com.kevin.rxjavaandretrofit.Model.Movie;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kevinkj_Lin on 2018/5/11.
 */

public class ApiMethods {
    // 封裝 subscribe 和 observer 的 thread
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    // 取得 豆瓣 電影的 top movie
    public static void getTopMovie(Observer<Movie> observer, int start, int count) {
        ApiSubscribe(Api.getApiService().getTopMovie(start, count), observer);
    }
}
