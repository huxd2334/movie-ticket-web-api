package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.RoomDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomDetailRepo extends CrudRepository<RoomDetailEntity,Integer> {
    List<RoomDetailEntity> findAllByRoom(int room);
}
