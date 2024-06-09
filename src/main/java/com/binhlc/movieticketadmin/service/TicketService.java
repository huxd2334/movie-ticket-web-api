package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.*;
import com.binhlc.movieticketadmin.domain.repo.*;
import com.binhlc.movieticketadmin.domain.reponse.TicketRoomResponse;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.reponse.TicketDetailResponse;
import com.binhlc.movieticketadmin.domain.reponse.TransWithMovieTimeReponse;
import com.binhlc.movieticketadmin.domain.request.TicketListRequest;
import com.binhlc.movieticketadmin.domain.request.UnholdTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService extends BaseService {

    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private RoomDetailRepo roomDetailRepo;

    @Autowired
    private RoomRepo roomRepo;
    
    @Autowired
    private MovieTimeRepo movieTimeRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private DetailTransactionRepo detailTransactionRepo;

//    public Result getHistoryTransaction(Integer id, String token) {
//        ErrorType errorType = userService.checkToken(id, token);
//        if (errorType == null) {
//            List<TransactionEntity> list = transactionRepo.findAllByIdUserAndTypeOrderByTimeDesc(id,2);
//            return Result.success(list);
//        } else {
//            return Result.fail(errorType);
//        }
//    }

    public Result getHistoryTransaction(Integer id, String token) {
        ErrorType errorType = userService.checkToken(id, token);
        if (errorType == null) {
            List<TransactionEntity> transactions = transactionRepo.findAllByIdUserAndTypeOrderByTimeDesc(id,2);
            List<TransWithMovieTimeReponse> responseList = new ArrayList<>();
            for (TransactionEntity transaction : transactions) {
                List<DetailTransactionEntity> detailList = detailTransactionRepo.findAllByIdTransaction(transaction.getId());
                for(DetailTransactionEntity detail : detailList){
                    TicketEntity ticket = ticketRepo.findById(detail.getIdTicket()).orElse(new TicketEntity());
                    Integer idMovieTime = ticket.getIdMovieTime();
                    MovieTimeEntity movieTime = movieTimeRepo.findById(idMovieTime).orElse(new MovieTimeEntity());
                    MovieEntity movie = movieRepo.findById(movieTime.getIdMovie()).orElse(new MovieEntity());
                    RoomDetailEntity roomDetail = roomDetailRepo.findById(ticket.getIdRoomDetail()).orElse(new RoomDetailEntity());
                    RoomEntity room = roomRepo.findById(roomDetail.getRoom()).orElse(new RoomEntity());
                    responseList.add(new TransWithMovieTimeReponse(transaction, movieTime, movie, roomDetail, room));
                }
            }
            return Result.success(responseList);
        } else {
            return Result.fail(errorType);
        }
    }

    public Result getListTicket(Integer id, String token, Integer idTrans) {
        ErrorType errorType = userService.checkToken(id, token);
        if (errorType == null) {
            List<DetailTransactionEntity> list = detailTransactionRepo.findAllByIdTransaction(idTrans);
            List<TicketRoomResponse>  listResposeTicket = new ArrayList<>();
            for(DetailTransactionEntity temp : list){
                TicketRoomResponse ticketRoomResponse = new TicketRoomResponse();
                Optional<TicketEntity> optionalTicket  = ticketRepo.findById(temp.getIdTicket());
                if(optionalTicket.isPresent()){
                    ticketRoomResponse.setTicketEntity(optionalTicket.get());
                    Optional<RoomDetailEntity> optionalZoomDetai = roomDetailRepo.findById(optionalTicket.get().getIdRoomDetail());
                    if(optionalZoomDetai.isPresent()){
                        ticketRoomResponse.setRoomDetailEntity(optionalZoomDetai.get());
                    }else {
                        ticketRoomResponse.setRoomDetailEntity(new RoomDetailEntity());
                    }
                }else {
                    ticketRoomResponse.setTicketEntity(new TicketEntity());
                    ticketRoomResponse.setRoomDetailEntity(new RoomDetailEntity());
                }
                listResposeTicket.add(ticketRoomResponse);
            }
            return Result.success(listResposeTicket);
        } else {
            return Result.fail(errorType);
        }
    }

    public Result getTicket(Integer id, String token, Integer idMovieTime) {
        ErrorType errorType = userService.checkToken(id, token);
        if (errorType == null) {
            List<TicketEntity> mListTicket = ticketRepo.getListTicket(idMovieTime);
            List<TicketDetailResponse> mListRes = new ArrayList<>();
            List<DetailTransactionEntity> mDTrans = new ArrayList<>();
            if (mListTicket != null) {
                for (TicketEntity temp : mListTicket) {
                    TicketDetailResponse response = new TicketDetailResponse();
                    response.setTicket(temp);
                    Optional<RoomDetailEntity> roomDetail = roomDetailRepo.findById(temp.getIdRoomDetail());
                    if (roomDetail.isPresent()) {
                        response.setPosition(roomDetail.get());
                    } else {
                        response.setPosition(new RoomDetailEntity());
                    }
                    mListRes.add(response);
                }
                return Result.success(mListRes);
            }
        } else {
            return Result.fail(errorType);
        }
        return null;
    }

    public Result holderTicket(Integer id, String token, TicketListRequest bodyListTicket) {
        ErrorType errorType = userService.checkToken(id, token);
        if (errorType == null) {
            List<Integer> list = bodyListTicket.getListId();
            List<TicketEntity> mListTicket = new ArrayList<>();
            TransactionEntity tranEntity = new TransactionEntity();
            List<DetailTransactionEntity> listTran = new ArrayList<>();
            tranEntity.setIdUser(id);
            tranEntity.setType(1);
            tranEntity.setStatus(1);
            tranEntity.setTime(currrentTime());
            transactionRepo.save(tranEntity);
            for (Integer idTicket : list) {
                Optional<TicketEntity> optional = ticketRepo.findById(idTicket);
                if (optional.isPresent()) {
                    TicketEntity entity = optional.get();
                    DetailTransactionEntity detailTran = new DetailTransactionEntity();
                    if (entity.getStatus() == 1) {
                        entity.setStatus(3);
                        mListTicket.add(entity);
                        detailTran.setIdTransaction(tranEntity.getId());
                        detailTran.setIdTicket(entity.getIdTicket());
                        listTran.add(detailTran);
                    } else {
                        break;
                    }
                }
            }
            if (mListTicket.size() == list.size()) {
                ticketRepo.saveAll(mListTicket);
                detailTransactionRepo.saveAll(listTran);
                return Result.success(tranEntity);
            } else {
                transactionRepo.delete(tranEntity);
                return Result.fail(ErrorType.TICKET_IS_HOLDING);
            }

        } else {
            return Result.fail(errorType);
        }
    }

    public Result booking(Integer id, String token, UnholdTicketRequest unHoldTicketRequest) {
        ErrorType errorType = userService.checkToken(id, token);
        if (errorType == null) {
            Optional<TransactionEntity> tranEntity = transactionRepo.findById(unHoldTicketRequest.getIdTran());
            if(tranEntity.isPresent()){
                TransactionEntity transactionEntity = tranEntity.get();
                List<DetailTransactionEntity> detailTransactionEntityList = detailTransactionRepo.findAllByIdTransaction(transactionEntity.getId());
                List<TicketEntity> mListTicket = new ArrayList<>();
                int tong = 0;
                for (DetailTransactionEntity temp: detailTransactionEntityList){
                    Optional<TicketEntity> entity = ticketRepo.findById(temp.getIdTicket());
                    if(entity.isPresent()){
                        TicketEntity ticket = entity.get();
                        ticket.setStatus(2);
                        tong = tong+ticket.getPrice();
                        mListTicket.add(ticket);
                    }
                }
                tranEntity.get().setType(2);
                UserEntity userEntity = userService.findByUserId(id);
                userEntity.setBalance(userEntity.getBalance()+tong);
                int point = tong/1000;
                userEntity.setPoint(userEntity.getPoint()+point);
                ticketRepo.saveAll(mListTicket);
                transactionRepo.save(tranEntity.get());
                return Result.success();
            }else {
                return Result.fail(ErrorType.OBJECT_EMPTY);
            }

        } else {
            return Result.fail(errorType);
        }
    }

    public Result unHolderTicket(Integer id, String token, UnholdTicketRequest unHoldTicketRequest) {
        ErrorType errorType = userService.checkToken(id, token);
        if (errorType == null) {
            Optional<TransactionEntity> tranEntity = transactionRepo.findById(unHoldTicketRequest.getIdTran());
            if(tranEntity.isPresent()){
                TransactionEntity transactionEntity = tranEntity.get();
                List<DetailTransactionEntity> detailTransactionEntityList = detailTransactionRepo.findAllByIdTransaction(transactionEntity.getId());
                List<TicketEntity> mListTicket = new ArrayList<>();
                for (DetailTransactionEntity temp: detailTransactionEntityList){
                    Optional<TicketEntity> entity = ticketRepo.findById(temp.getIdTicket());
                    if(entity.isPresent()){
                        TicketEntity ticket = entity.get();
                        ticket.setStatus(1);
                        mListTicket.add(ticket);
                    }
                }
                ticketRepo.saveAll(mListTicket);
                transactionRepo.delete(transactionEntity);
                detailTransactionRepo.deleteAll(detailTransactionEntityList);
                return Result.success();
            }else {
                return Result.fail(ErrorType.OBJECT_EMPTY);
            }

        } else {
            return Result.fail(errorType);
        }
    }

}
