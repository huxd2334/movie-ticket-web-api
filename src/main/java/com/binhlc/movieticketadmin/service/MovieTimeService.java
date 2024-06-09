package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.MovieTimeEntity;
import com.binhlc.movieticketadmin.domain.entity.TicketEntity;
import com.binhlc.movieticketadmin.domain.entity.RoomDetailEntity;
import com.binhlc.movieticketadmin.domain.repo.MovieTimeRepo;
import com.binhlc.movieticketadmin.domain.repo.TicketRepo;
import com.binhlc.movieticketadmin.domain.repo.RoomDetailRepo;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.MovieTimeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieTimeService extends BaseService{
    @Autowired
    private MovieTimeRepo movieTimeRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private RoomDetailRepo roomDetailRepo;
    @Autowired
    private UserService userService;

    public Result addMovieTime(MovieTimeRequest request){
        MovieTimeEntity entity = new MovieTimeEntity();
        entity.setIdMovie(request.getIdMovie());
        entity.setTimeStart(request.getTime());
        entity.setDateStart(request.getDate());
        entity.setPrice(request.getPrice());
        entity.setIdRoom(request.getIdRoom());
        entity.setCreateBy(1);
        entity.setUpdateBy(1);
        entity.setTimeCreate(currrentTime());
        entity.setTimeUpdate(currrentTime());
        movieTimeRepo.save(entity);
        List<RoomDetailEntity> roomDetailList = roomDetailRepo.findAllByRoom(request.getIdRoom());
        List<TicketEntity> ticketList = new ArrayList<>();
        for(RoomDetailEntity temp: roomDetailList){
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setIdMovieTime(entity.getId());
            ticketEntity.setIdRoomDetail(temp.getId());
            ticketEntity.setStatus(1);
            ticketEntity.setCreateBy(1);
            ticketEntity.setUpdateBy(1);
            ticketEntity.setTimeCreate(currrentTime());
            ticketEntity.setTimeUpdate(currrentTime());
            if(temp.getType()==1)
                ticketEntity.setPrice(request.getPrice());
            else if(temp.getType()==2)
                ticketEntity.setPrice(request.getPrice()*2);
            else
                ticketEntity.setPrice(request.getPrice());
            ticketList.add(ticketEntity);
        }
        ticketRepo.saveAll(ticketList);
        return Result.success();
    }

    public Result getMovieTimeByIDandDate(Integer id, String token, Integer idMovie, Integer date){
        ErrorType type = userService.checkToken(id, token);
        if (type == null) {
            Date time = new Date();
            SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMdd");
            String timeNow = sdf.format(time);
            int timeInt = 0;
            try {
                timeInt = Integer.parseInt(timeNow);
            } catch (Exception e) {
                logger.error("Exception {}", e);
            }
            int timeStart = 0;
            if (timeInt == date) {
                int hh = time.getHours();
                int mm = time.getMinutes();
                timeStart = hh * 100 + mm;
            }
            List<MovieTimeEntity> mEntities = movieTimeRepo.getAllByIdAndDate(idMovie, date, timeStart);
            if (mEntities != null) {
                return Result.success(mEntities);
            } else {
                return Result.fail(ErrorType.LIST_EMPTY);
            }
        } else {
            return Result.fail(type);
        }
    }
}
