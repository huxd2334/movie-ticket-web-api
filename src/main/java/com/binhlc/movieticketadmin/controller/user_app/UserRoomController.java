package com.binhlc.movieticketadmin.controller.user_app;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/room")
public class UserRoomController extends BaseController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/get")
    public Result getRoomInfo(@RequestHeader("token") String token,
                              @RequestParam Integer idRoom) {
        logger.info("[User Room Controller] Get Room Info: token {} and idRoom {}", token, idRoom);
        if (token != null && idRoom != null) {
            Integer id = checkToken(token);
            if (id != null) {
                Result result = roomService.getRoomById(id, token, idRoom);
                return result;
            } else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        } else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }

}
