package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.DetailTransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetailTransactionRepo extends CrudRepository<DetailTransactionEntity,Integer> {
    DetailTransactionEntity findByIdTicket(int idTicket);
    List<DetailTransactionEntity> findAllByIdTransaction(int idTrans);
}
