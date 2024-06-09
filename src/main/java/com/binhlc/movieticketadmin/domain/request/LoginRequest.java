package com.binhlc.movieticketadmin.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    private String username, password;
    public boolean isVadialte(){
        
        return !username.isEmpty() && !password.isEmpty() ;
    }

    @Override
    public String toString() {
        return "LoginRequest{+" +
                "username'"+username+"',"+
                "password'"+password+"',"+
                "'}";
    }
}
