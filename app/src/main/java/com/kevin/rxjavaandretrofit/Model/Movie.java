package com.kevin.rxjavaandretrofit.Model;

import java.util.List;

/**
 * Created by Kevinkj_Lin on 2018/5/11.
 */

public class Movie {
    private String title;
    private List<Subjects> subjects;
    public String getTitle() {
        return title;
    }
    public List<Subjects> getSubjects() {
        return subjects;
    }
}
