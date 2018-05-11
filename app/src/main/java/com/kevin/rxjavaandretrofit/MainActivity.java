package com.kevin.rxjavaandretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kevin.rxjavaandretrofit.Model.Movie;
import com.kevin.rxjavaandretrofit.Model.Subjects;
import com.kevin.rxjavaandretrofit.Service.ApiMethods;
import com.kevin.rxjavaandretrofit.Service.MyObserver;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "XXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 換成 MyObserver，封裝 Observer 的 onSubscribe, onError, onComplete
        MyObserver.ObserverOnNextListener<Movie> listener = new MyObserver.ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        };
        ApiMethods.getTopMovie(new MyObserver<Movie>(this, listener), 0, 10);
    }
}
