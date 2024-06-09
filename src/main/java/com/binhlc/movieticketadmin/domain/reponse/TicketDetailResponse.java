package com.binhlc.movieticketadmin.domain.reponse;

import com.binhlc.movieticketadmin.domain.entity.TicketEntity;
import com.binhlc.movieticketadmin.domain.entity.RoomDetailEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketDetailResponse {
    private TicketEntity ticket;
    private RoomDetailEntity position;
}
