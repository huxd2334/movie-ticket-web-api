package com.binhlc.movieticketadmin.domain.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserChangeProfileRequest extends BaseRequest{
    private String lastName, name, phone, address;
    private Long birthday;
    private Integer gender;
    @Override
    public boolean isValidate() {
        if(isNullOrEmpty(lastName) || isNullOrEmpty(name) || isNullOrEmpty(phone) || isNullOrEmpty(address) || birthday == null || gender == null){
            return false;
        }
        return true;
    }
}
