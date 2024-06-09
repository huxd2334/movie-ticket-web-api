package com.binhlc.movieticketadmin.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserChangePasswordRequest extends BaseRequest {
    @JsonProperty("oldPassword")
    private String oldPassword;

    @JsonProperty("newPassword")
    private String newPassword;

    @Override
    public boolean isValidate() {
        if(isNullOrEmpty(oldPassword) || isNullOrEmpty(newPassword)){
            return false;
        }
        return true;
    }

}
