package com.binhlc.movieticketadmin.controller.user_app;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.entity.UserEntity;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.UserChangePasswordRequest;
import com.binhlc.movieticketadmin.domain.request.UserChangeProfileRequest;
import com.binhlc.movieticketadmin.domain.request.UserLoginRequest;
import com.binhlc.movieticketadmin.domain.request.UserRegisterRequest;
import com.binhlc.movieticketadmin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
public class UserAuthController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginRequest request) {
//        logger.info("[User Auth Controller] Login request: {}", request);
        Result result = null;
        if(request.isValidate())
            result = userService.login(request);
        else
            result = Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterRequest request) {
//        logger.info("[User Auth Controller] Register request: {}", request);
        Result result = null;
        if(request.isValidate())
            result = userService.register(request);
        else
            result = Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        return result;
    }

    @PostMapping("/logout")
    public Result logout(@RequestHeader("token") String token) {
//        logger.info("[User Auth Controller] Logout token: {}", token);
        Result result = null;
        if(token!=null && !token.isEmpty()) {
            if(checkToken(token)!=null)
                result = userService.logout(checkToken(token), token);
            else
                result = Result.fail(ErrorType.TOKEN_IN_VALID);
        } else {
            result = Result.fail(ErrorType.TOKEN_IS_EMPTY);
        }
        return result;
    }

    @GetMapping("/profile")
    public Result getProfile(@RequestHeader("token") String token) {
        logger.info("[User Auth Controller] Get profile token: {}", token);
        Result result = null;
        if(token!=null && !token.isEmpty()) {
            if(checkToken(token)!=null)
                result = userService.getProfile(checkToken(token), token);
            if (result.isSuccess()) {
                UserEntity user = (UserEntity) result.getData();
                logger.info("[User Auth Controller] User's last name: {}", user.getLastName());
            }
            else
                result = Result.fail(ErrorType.TOKEN_IN_VALID);
        } else {
            result = Result.fail(ErrorType.TOKEN_IS_EMPTY);
        }
        return result;
    }

    @PutMapping("/change_profile")
    public Result changeProfile(@RequestHeader("token") String token, @RequestBody UserChangeProfileRequest request) {
        logger.info("[User Auth Controller] Change profile requestBody: {}" ,request);
        Result result = null;
        if(token!=null && !token.isEmpty()) {
            if(checkToken(token)!=null) {
                if(request.isValidate())
                    result = userService.changeProfile(checkToken(token), token, request);
                else
                    result = Result.fail(ErrorType.TOKEN_IN_VALID);
            }
            else
                result = Result.fail(ErrorType.TOKEN_IN_VALID);
        } else {
            result = Result.fail(ErrorType.TOKEN_IS_EMPTY);
        }
        return result;
    }

    @PutMapping("/change_password")
    public Result changePassword(@RequestHeader("token") String token,
                                 @RequestBody UserChangePasswordRequest request) {
        logger.info("[User Auth Controller] Change password requestBody: {}" ,request);
        Result result = null;
        if(token!=null && !token.isEmpty()) {
            if(checkToken(token)!=null) {
                if(request.isValidate())
                    result = userService.changePassword(checkToken(token), token, request);
                else
                    result = Result.fail(ErrorType.ARGUMENT_NOT_VALID);
            }
            else
                result = Result.fail(ErrorType.TOKEN_IN_VALID);
        } else {
            result = Result.fail(ErrorType.TOKEN_IS_EMPTY);
        }
        return result;
    }




}
