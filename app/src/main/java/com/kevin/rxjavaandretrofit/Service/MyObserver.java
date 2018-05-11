package com.kevin.rxjavaandretrofit.Service;

import android.content.Context;
import android.util.Log;

import com.kevin.rxjavaandretrofit.ProgressDialogHandler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kevinkj_Lin on 2018/5/11.
 */

public class MyObserver<T> implements Observer<T>, ProgressDialogHandler.ProgressCancelListener {
    private static final String TAG = "MyObserver";
    private ObserverOnNextListener listener;
    private Context context;

    private ProgressDialogHandler mProgressDialogHandler;
    private Disposable d;

    public interface ObserverOnNextListener<T> {
        void onNext(T t);
    }

    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }
    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
                    .sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        // 可以加入額外處理
        this.d = d;
        showProgressDialog();
    }
    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        // 可以加入額外處理
        dismissProgressDialog();
    }
    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        // 可以加入額外處理
        dismissProgressDialog();

    }

    @Override
    public void onCancelProgress() {
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}