package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.domain.entity.UserEntity;

public interface IUserService {
    int save(UserEntity userEntity);
    UserEntity findByUserId(Integer id);
    UserEntity findByEmail(String email);
}
