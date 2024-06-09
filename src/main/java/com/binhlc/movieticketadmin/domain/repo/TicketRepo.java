package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.TicketEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepo extends CrudRepository<TicketEntity,Integer> {
    @Query(value = "SELECT * FROM ticket " +
            "WHERE id_movie_time = :idMovie " +
            "ORDER BY id_room_detail asc",nativeQuery = true)
    List<TicketEntity> getListTicket(@Param("idMovie") Integer idMovie);

    boolean existsByIdMovieTime(Integer idMovieTime);
}
