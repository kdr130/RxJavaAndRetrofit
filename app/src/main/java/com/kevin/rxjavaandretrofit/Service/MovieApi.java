package com.kevin.rxjavaandretrofit.Service;

import com.kevin.rxjavaandretrofit.Model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kevinkj_Lin on 2018/5/11.
 */

public interface MovieApi {
    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}
