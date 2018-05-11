package com.kevin.rxjavaandretrofit.Service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevinkj_Lin on 2018/5/11.
 */

public class Api {
    public static String baseUrl = "https://api.douban.com/v2/movie/";
    private static MovieApi apiService;

    // get single instance
    public static MovieApi getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }
    private Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                // 將 response 使用 Gson 格式處理
                // 需要在 build.gradle 加入 compile 'com.squareup.retrofit2:converter-gson:2.3.0'
                .addConverterFactory(GsonConverterFactory.create())
                // 搭配 RxJava2.0 則要加入 RxJava2CallAdapterFactory.create()
                // 需要在 build.gradle 加入 compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(MovieApi.class);
    }
}
