package com.binhlc.movieticketadmin.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserLoginRequest extends BaseRequest {
    private String email, password;

    @Override
    public boolean isValidate() {
        if(isNullOrEmpty(email)||isNullOrEmpty(password)){
            return false;
        }
        return true;
    }
}
