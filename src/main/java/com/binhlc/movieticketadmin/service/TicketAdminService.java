package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.MovieTimeEntity;
import com.binhlc.movieticketadmin.domain.entity.RoomDetailEntity;
import com.binhlc.movieticketadmin.domain.entity.TicketEntity;
import com.binhlc.movieticketadmin.domain.repo.MovieTimeRepo;
import com.binhlc.movieticketadmin.domain.repo.RoomDetailRepo;
import com.binhlc.movieticketadmin.domain.repo.TicketRepo;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.AddTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketAdminService extends BaseService{
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private RoomDetailRepo roomDetailRepo;
    @Autowired
    private MovieTimeRepo movieTimeRepo;

    public Result addTicket(AddTicketRequest request) {
        if(!movieTimeRepo.existsById(request.getIdMovieTime())){
            return Result.fail(ErrorType.MOVIE_TIME_NOT_FOUND);
        }else if(ticketRepo.existsByIdMovieTime(request.getIdMovieTime())){
            return Result.fail(ErrorType.TICKET_CREATED_BEFORE);
        } else{
            MovieTimeEntity movieTime = movieTimeRepo.findById(request.getIdMovieTime()).get();
            Integer price = movieTime.getPrice();
            Integer idRoom = movieTime.getIdRoom();
            List<RoomDetailEntity> roomList = roomDetailRepo.findAllByRoom(idRoom);
            for(RoomDetailEntity roomDetailEntity : roomList){
                TicketEntity ticket = new TicketEntity();
                ticket.setPrice(price);
                ticket.setIdMovieTime(request.getIdMovieTime());
                ticket.setStatus(1);
                ticket.setCreateBy(1);
                ticket.setUpdateBy(1);
                ticket.setIdRoomDetail(roomDetailEntity.getId());
                ticket.setTimeCreate(currrentTime());
                ticket.setTimeUpdate(currrentTime());
                ticketRepo.save(ticket);
            }
            return Result.success();
        }
    }
}
