package com.binhlc.movieticketadmin.controller.admin_web;

import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.AddRoomRequest;
import com.binhlc.movieticketadmin.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/room")
public class RoomController extends BaseController {
    @Autowired
    private RoomService roomService;
    @PostMapping("/add")
    public Result addRoom(@RequestBody AddRoomRequest addRoomRequest) {
        Result result = roomService.add(addRoomRequest);
        return result;
    }

}
