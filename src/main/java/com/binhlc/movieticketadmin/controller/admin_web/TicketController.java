package com.binhlc.movieticketadmin.controller.admin_web;

import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.AddTicketRequest;
import com.binhlc.movieticketadmin.service.TicketAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/ticket")
public class TicketController {

    @Autowired
    private TicketAdminService ticketAdminService;
    @PostMapping("/add")
    public Result addTicket(@RequestBody AddTicketRequest request){
        Result result = ticketAdminService.addTicket(request);
        return result;
    }
}
