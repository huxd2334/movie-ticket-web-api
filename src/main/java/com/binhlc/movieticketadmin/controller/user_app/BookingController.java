package com.binhlc.movieticketadmin.controller.user_app;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.TicketListRequest;
import com.binhlc.movieticketadmin.domain.request.UnholdTicketRequest;
import com.binhlc.movieticketadmin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/ticket")
public class BookingController extends BaseController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/get")
    public Result getTicket(@RequestHeader("token")String token,
                            @RequestParam Integer idMovieTime) {
        logger.info("[Ticket User Get] Get ticket by idMovieTime {}",idMovieTime);
        if(token!=null && idMovieTime!=null) {
            Integer idUser = checkToken(token);
            if(idUser!=null) {
                Result result = ticketService.getTicket(idUser,token,idMovieTime);
                return result;
            } else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        } else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }

    }

    @PutMapping("/hold")
    public Result holder(@RequestHeader("token")String token,
                         @RequestBody TicketListRequest bodyListTicket){
        logger.info("[Ticket User Holder] Holder ticket by idTicket {}",bodyListTicket);
        if(token!=null && bodyListTicket.isValidate()){
            Integer id = checkToken(token);
            if(id!=null){
                Result result = ticketService.holderTicket(id,token,bodyListTicket);
                return result;
            }else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        }else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }

    @PutMapping("/unhold")
    public Result unHolder(@RequestHeader("token")String token,
                           @RequestBody UnholdTicketRequest unholdTicketRequest){
        logger.info("[Ticket User Unholder] Unholder ticket by idTicket {}", unholdTicketRequest);
        if(token!=null && unholdTicketRequest.isValidate()){
            Integer id = checkToken(token);
            if(id!=null){
                Result result = ticketService.unHolderTicket(id,token, unholdTicketRequest);
                return result;
            }else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        }else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }

    @PutMapping("/booking")
    public Result booking(@RequestHeader("token")String token,
                          @RequestBody UnholdTicketRequest unholdTicketRequest){
        logger.info("[Ticket User Booking] Booking ticket by idTrans {}", unholdTicketRequest);
        if(token!=null && unholdTicketRequest.isValidate()){
            Integer id = checkToken(token);
            if(id!=null){
                Result result = ticketService.booking(id,token, unholdTicketRequest);
                return result;
            }else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        }else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }

    @GetMapping("/getTicketBook")
    public Result getListTicket(@RequestHeader("token")String token,
                                @RequestParam Integer idTrans){
        logger.info("[Ticket User getListTicket] getListTicket ticket by idTrans {}",idTrans);
        if(token!=null && idTrans!=null){
            Integer id = checkToken(token);
            if(id!=null){
                Result result = ticketService.getListTicket(id,token,idTrans);
                return result;
            }else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        }else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }

    @GetMapping("/history_transaction")
    public Result getHistoryTransaction(@RequestHeader("token")String token){
        logger.info("[Ticket User get history transaction] get history transaction  by token {}",token);
        if(token!=null){
            Integer id = checkToken(token);
            if(id!=null){
                Result result = ticketService.getHistoryTransaction(id,token);
                return result;
            }else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        }else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }
}
