package com.binhlc.movieticketadmin.domain.repo;

import com.binhlc.movieticketadmin.domain.entity.UserAdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepo extends CrudRepository<UserAdminEntity, Integer> {
    UserAdminEntity findByUsername(String username);
}
