package com.kevin.rxjavaandretrofit.Service;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kevinkj_Lin on 2018/5/11.
 */

public class MyObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";
    private ObserverOnNextListener listener;
    private Context context;

    public interface ObserverOnNextListener<T> {
        void onNext(T t);
    }

    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
    }
    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        // 可以加入額外處理
    }
    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        // 可以加入額外處理
    }
    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        // 可以加入額外處理
    }
}