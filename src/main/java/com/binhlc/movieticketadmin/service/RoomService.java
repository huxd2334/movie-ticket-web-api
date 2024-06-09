package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.RoomEntity;
import com.binhlc.movieticketadmin.domain.entity.RoomDetailEntity;
import com.binhlc.movieticketadmin.domain.repo.RoomDetailRepo;
import com.binhlc.movieticketadmin.domain.repo.RoomRepo;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.AddRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService extends BaseService {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RoomDetailRepo roomDetailRepo;
    @Autowired
    private UserService userService;

    public Result add(AddRoomRequest addRoomRequest){
        RoomEntity mRoomEntity = new RoomEntity();
        mRoomEntity.setName(addRoomRequest.getName());
        mRoomEntity.setNumCol(addRoomRequest.getCol());
        mRoomEntity.setNumRow(addRoomRequest.getRow());
        mRoomEntity.setCreateBy(1);
        mRoomEntity.setUpdateBy(1);
        Timestamp current = currrentTime();
        mRoomEntity.setTimeCreate(current);
        mRoomEntity.setTimeUpdate(current);
        roomRepo.save(mRoomEntity);
        int index =1;
        List<RoomDetailEntity> list = new ArrayList<>();
        for(int i = 1; i<= addRoomRequest.getRow(); i++){
            for(int j = 1; j<= addRoomRequest.getCol(); j++){
                RoomDetailEntity roomDetailEntity = new RoomDetailEntity();
                roomDetailEntity.setRoww(i);
                roomDetailEntity.setCol(j);
                roomDetailEntity.setPosition(index);
                roomDetailEntity.setType(1);
                roomDetailEntity.setRoom(mRoomEntity.getIdRoom());
                index++;
                list.add(roomDetailEntity);
            }
        }
        roomDetailRepo.saveAll(list);
        return Result.success();
    }

    public Result getRoomById(Integer id, String token, Integer idRoom){
        ErrorType errorType = userService.checkToken(id,token);
        if(errorType==null){
            Optional<RoomEntity> optional = roomRepo.findById(idRoom);
            if(optional.isPresent()){
                return Result.success(optional.get());
            }else {
                return Result.fail(ErrorType.OBJECT_EMPTY);
            }
        }else {
            return Result.fail(errorType);
        }
    }


}
