package com.binhlc.movieticketadmin.domain.reponse;

import com.binhlc.movieticketadmin.domain.entity.CategoryEntity;
import com.binhlc.movieticketadmin.domain.entity.MovieEntity;

import java.util.List;

public class MovieResponse {
    private MovieEntity movie;
    private List<CategoryEntity> categories;

    public MovieResponse() {
    }

    public MovieResponse(MovieEntity movie, List<CategoryEntity> categories) {
        this.movie = movie;
        this.categories = categories;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
