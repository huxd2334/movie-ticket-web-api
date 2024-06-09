package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends CrudRepository<MovieEntity,Integer> {
    //List<MovieEntity> findAll();

    @Query(value = "SELECT * FROM movie_ticket.movie " +
            "WHERE movie_ticket.movie.part_time < :part_date " +
            "AND status = 1 limit :start,:limit  ", nativeQuery = true)
    List<MovieEntity> getListMovieIsShowing(@Param("part_date")int part_date,
                                            @Param("start")int start,
                                            @Param("limit")int limit);

    @Query(value = "SELECT * FROM movie_ticket.movie " +
            "WHERE movie_ticket.movie.part_time > :part_date " +
            "AND status = 1 limit :start,:limit ", nativeQuery = true)
    List<MovieEntity> getListMovieCommingSoon(@Param("part_date")int part_date,
                                            @Param("start")int start,
                                            @Param("limit")int limit);
    @Query(value = "SELECT  * FROM movie_ticket.movie " +
            "ORDER BY movie_ticket.movie.view " +
            "AND status = 1 desc limit 5" ,nativeQuery = true)
    List<MovieEntity> getTopView();

}
