package com.kevin.rxjavaandretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kevin.rxjavaandretrofit.Model.Movie;
import com.kevin.rxjavaandretrofit.Model.Subjects;
import com.kevin.rxjavaandretrofit.Service.ApiService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "XXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                // 將 response 使用 Gson 格式處理
                // 需要在 build.gradle 加入 compile 'com.squareup.retrofit2:converter-gson:2.3.0'
                .addConverterFactory(GsonConverterFactory.create())
                // 搭配 RxJava2.0 則要加入 RxJava2CallAdapterFactory.create()
                // 需要在 build.gradle 加入 compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }
                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "onNext: " + movie.getTitle());
                        List<Subjects> list = movie.getSubjects();
                        for (Subjects sub : list) {
                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Over!");
                    }
                });
    }
}
