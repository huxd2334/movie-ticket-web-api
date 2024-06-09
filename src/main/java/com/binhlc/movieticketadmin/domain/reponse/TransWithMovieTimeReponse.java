package com.binhlc.movieticketadmin.domain.reponse;

import com.binhlc.movieticketadmin.domain.entity.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransWithMovieTimeReponse {
    private TransactionEntity transactionEntity;
    private MovieTimeEntity movieTimeEntity;
    private MovieEntity movieEntity;
    private RoomDetailEntity roomDetailEntity;
    private RoomEntity roomEntity;

    public TransWithMovieTimeReponse(TransactionEntity transactionEntity, MovieTimeEntity movieTimeEntity, MovieEntity movieEntity, RoomDetailEntity roomDetailEntity, RoomEntity roomEntity) {
        this.transactionEntity = transactionEntity;
        this.movieTimeEntity = movieTimeEntity;
        this.movieEntity = movieEntity;
        this.roomDetailEntity = roomDetailEntity;
        this.roomEntity = roomEntity;
    }
}
