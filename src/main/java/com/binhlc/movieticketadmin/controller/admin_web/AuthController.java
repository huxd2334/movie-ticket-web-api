package com.binhlc.movieticketadmin.controller.admin_web;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.LoginRequest;
import com.binhlc.movieticketadmin.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    UserAdminService userAdminService;
    @PostMapping(value="/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        logger.info("[Authentication Controller] Authentication Login Body: {}",loginRequest);
        Result result;
        if(loginRequest.isVadialte()) {
            result = userAdminService.login(loginRequest);
        } else {
            result = Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
        return result;
    }


}
