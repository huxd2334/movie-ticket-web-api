package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity,Integer> {
    UserEntity findByEmail(String email);
}
