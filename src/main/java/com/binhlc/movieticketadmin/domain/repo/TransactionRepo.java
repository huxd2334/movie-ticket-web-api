package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<TransactionEntity,Integer> {
    @Query(value = "")
    List<TransactionEntity> findAllByIdUserAndTypeOrderByTimeDesc(Integer idUser, int type);
}
