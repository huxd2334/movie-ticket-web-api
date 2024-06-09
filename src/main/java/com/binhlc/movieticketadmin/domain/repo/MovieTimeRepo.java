package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.MovieTimeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieTimeRepo extends CrudRepository<MovieTimeEntity,Integer> {
    @Query(value = "SELECT * FROM movie_time " +
            "WHERE id_movie = :id " +
            "AND date_start = :date " +
            "AND time_start > :time " +
            "ORDER BY time_start ASC", nativeQuery = true)
    List<MovieTimeEntity> getAllByIdAndDate(@Param("id") int idMovie,
                                            @Param("date")int date,
                                            @Param("time")int time);
}
