package com.binhlc.movieticketadmin.domain.reponse;

import com.binhlc.movieticketadmin.domain.entity.RoomDetailEntity;
import com.binhlc.movieticketadmin.domain.entity.TicketEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketRoomResponse {
    private TicketEntity ticketEntity;
    private RoomDetailEntity roomDetailEntity;
}
