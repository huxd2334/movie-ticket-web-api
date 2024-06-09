package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.MovieCategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieCategoryRepo extends CrudRepository<MovieCategoryEntity,Integer> {
    List<MovieCategoryEntity> findAllByIdMovie(Integer idMovie);
}
