package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.UserAdminEntity;
import com.binhlc.movieticketadmin.domain.repo.UserAdminRepo;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.LoginRequest;
import com.binhlc.movieticketadmin.util.JWTutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserAdminService extends BaseService {
    @Autowired
    private UserAdminRepo userAdminRepo;
    public UserAdminEntity findUserById(Integer userId) {
        return null;
    }

    public UserAdminEntity findUserByUsername(String username) {
        try {
            return userAdminRepo.findByUsername(username);
        } catch (Exception e) {
            logger.error("[User Admin Service] Error: {}", e);
            return null;
        }
    }



    public int save(UserAdminEntity userAdminEntity) {
        try{
            userAdminRepo.save(userAdminEntity);
            return 1;
        }catch (Exception e){
            logger.error("[User Admin Service] Error: {}", e);
            return 0;
        }
    }

    public int delete(UserAdminEntity userAdminEntity) {
        try{
            userAdminRepo.delete(userAdminEntity);
            return 1;
        }catch (Exception e){
            logger.error("[User Admin Service] Error: {}", e);
            return 0;
        }
    }

    public Result login(LoginRequest request){
        UserAdminEntity user = findUserByUsername(request.getUsername());
        if(user==null)
            return Result.fail(ErrorType.USER_NOT_EXIST);
        if(!request.getPassword().equals(user.getPassword()))
            return Result.fail(ErrorType.PASSWORD_WRONG);
        HashMap<String,String> map = new HashMap<>();
        map.put("user_id",String.valueOf(user.getId()));
        map.put("roles",String.valueOf(user.getRole()));
//                    map.put("device_id",String.valueOf(request.getDevice_id()));
        String token = JWTutils.generate(map);
//                    user.setLastLogin(currrentTime());
        user.setToken(token);
        save(user);
        return Result.success(user);
        }

}
