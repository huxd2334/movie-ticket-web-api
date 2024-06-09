package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.UserEntity;
import com.binhlc.movieticketadmin.domain.repo.UserRepo;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.UserChangePasswordRequest;
import com.binhlc.movieticketadmin.domain.request.UserChangeProfileRequest;
import com.binhlc.movieticketadmin.domain.request.UserLoginRequest;
import com.binhlc.movieticketadmin.domain.request.UserRegisterRequest;
import com.binhlc.movieticketadmin.util.JWTutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService extends BaseService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public int save(UserEntity userEntity) {
        try{
            userRepo.save(userEntity);
            return 1;
        }catch (Exception e){
            logger.error("[User Service] Error to save: {}", e);
            return 0;
        }
    }


    @Override
    public UserEntity findByUserId(Integer id) {
       try{
           Optional<UserEntity> userEntity = userRepo.findById(id);
           if(userEntity.isPresent()){
               return userEntity.get();
           }
           return null;
       }catch (Exception e){
           logger.error("[User Service] Error finding user by Id: {}", e);
           return null;
       }
    }

    @Override
    public UserEntity findByEmail(String email) {
        try{
            UserEntity userEntity = userRepo.findByEmail(email);
            return userEntity;
        }catch (Exception e){
            logger.error("[User Service] Error finding user by Email: {}", e);
            return null;
        }
    }

    public Result login(UserLoginRequest request) {
//        Result result = null;
//        UserEntity user = findByEmail(request.getEmail());
//        if(user!=null){
//
//                if(user.getPassword().equalsIgnoreCase(request.getPassword())){
//                    HashMap<String,String> map = new HashMap<>();
//                    map.put("user_id",String.valueOf(user.getId()));
//                    map.put("roles",String.valueOf(0));
//                    String token = JWTutils.generate(map);
//                    user.setToken(token);
//                    save(user);
//                    result = Result.success(user);
//            }else
//                result = Result.fail(ErrorType.PASSWORD_WRONG);
//        }else
//            result = Result.fail(ErrorType.USER_NOT_EXITS);
//        return result;
        UserEntity user = findByEmail(request.getEmail());
        if (user == null) {
            return Result.fail(ErrorType.USER_NOT_EXIST);
        }

        if (!user.getPassword().equalsIgnoreCase(request.getPassword())) {
            return Result.fail(ErrorType.PASSWORD_WRONG);
        }

        // Generate token
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(user.getId()));
        map.put("roles", String.valueOf(0)); // Adjust roles as necessary
        String token = JWTutils.generate(map);
        user.setToken(token);
        save(user);

        return Result.success(user);

//        if(user==null)
//            return Result.fail(ErrorType.USER_NOT_EXITS);
//
//        if (!user.getPassword().equals(request.getPassword()))
//            return Result.fail(ErrorType.PASSWORD_WRONG);
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("user_id", String.valueOf(user.getId()));
//        map.put("roles", String.valueOf(0));
//        String token = JWTutils.generate(map);
//        user.setToken(token);
//        save(user);
//        return Result.success(user);
    }

    public Result logout(Integer id, String token){
        UserEntity user = findByUserId(id);
       Result rs = null;
        if(user!=null) {
            user.setToken("");
            save(user);
            rs = Result.success();
        } else {
            rs = Result.fail();
        }
        return rs;
    }

    public Result register(UserRegisterRequest request){
        Result rs = null;
        UserEntity user = findByEmail(request.getEmail());
        if(user == null){
            UserEntity userEntity = new UserEntity();
            userEntity.setName(request.getName());
            userEntity.setLastName(request.getLastName());
            userEntity.setEmail(request.getEmail());
            userEntity.setPhone(request.getPhone());
            userEntity.setPassword(request.getPassword());
            userEntity.setAddress(request.getAddress());
            userEntity.setGender(request.getGender());
            userEntity.setBirthday(new Timestamp(request.getBirthday()));
            userEntity.setBalance(0);
            userEntity.setPoint(0);
            userEntity.setType(1);
            userEntity.setAvatar("");
            userEntity.setCreateBy(1);
            userEntity.setUpdateBy(1);
            userEntity.setToken("");
            userEntity.setTimeCreate(currrentTime());
            userEntity.setTimeUpdate(currrentTime());

            int save = save(userEntity);
            if (save==1)
                rs=Result.success();
            else
                rs=Result.fail();
        }else
            rs=Result.fail(ErrorType.USER_REGISTER_EXIST);
        return rs;
    }

    public  Result getProfile(Integer id, String token){
//        logger.info("[UserService] Fetching profile for user: {}", id);
        UserEntity user = findByUserId(id);
        Result result = null;
        if (user==null)
            return Result.fail(ErrorType.USER_NOT_EXIST);
        if(user.getToken()==null || !user.getToken().equalsIgnoreCase(token))
            return Result.fail(ErrorType.TOKEN_IN_VALID);
//        logger.info("[UserService] Fetched user profile: {}", user);
        return Result.success(user);
    }

    public Result changeProfile(Integer id, String token, UserChangeProfileRequest request){
//        logger.info("[UserService] Changing profile for user: {}", id);
        UserEntity user = findByUserId(id);
        Result result = null;
        if(user==null)
            return Result.fail(ErrorType.USER_NOT_EXIST);
        if(user.getToken()==null || !user.getToken().equalsIgnoreCase(token))
            return Result.fail(ErrorType.TOKEN_IN_VALID);
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setGender(request.getGender());
        user.setBirthday(new Timestamp(request.getBirthday()));
        user.setTimeUpdate(currrentTime());
        save(user);
        // Log the updated user
//        logger.info("[UserService] Updated user profile: {}", user);
        return Result.success(user);
    }

    public Result changePassword(Integer id, String token, UserChangePasswordRequest request){
        UserEntity user = findByUserId(id);
        Result result = null;
        if(user!=null){
            if(user.getToken().equalsIgnoreCase(token)){
                if(user.getPassword().equalsIgnoreCase(request.getOldPassword())){
                    user.setPassword(request.getNewPassword());
                    user.setTimeUpdate(currrentTime());
                    save(user);
                    result = Result.success();
            }else{
                result = Result.fail(ErrorType.PASSWORD_WRONG);
            }
        }else{
            result = Result.fail(ErrorType.TOKEN_IN_VALID);
        }
        }else
            result = Result.fail(ErrorType.TOKEN_IN_VALID);
        return result;
}


    public ErrorType checkToken(Integer id, String token){
        UserEntity user = findByUserId(id);
        if(user!=null) {
            if (user.getToken().equalsIgnoreCase(token))
                return null;
            else
                return ErrorType.TOKEN_IN_VALID;
        }
        return ErrorType.TOKEN_IN_VALID;
    }
}
