package com.binhlc.movieticketadmin.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserRegisterRequest extends BaseRequest {
    @Override
    public boolean isValidate() {
        if(isNullOrEmpty(email)||isNullOrEmpty(password)
                ||isNullOrEmpty(phone)||isNullOrEmpty(lastName)||isNullOrEmpty(name)||birthday ==null
                ||isNull(gender)||isNullOrEmpty(address)){
            return false;
        }
        return true;
    }

    private String email, password, lastName, name, phone, address;
    private Integer gender;
    private Long birthday;


}
