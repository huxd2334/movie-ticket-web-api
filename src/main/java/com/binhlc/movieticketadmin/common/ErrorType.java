package com.binhlc.movieticketadmin.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "")
public enum ErrorType {
    SYSTEM_ERROR("200", "SUCCESS"),
    ARGUMENT_NOT_VALID("002", "ARGUMENT_NOT_VALID"),
    USER_NOT_EXIST("003","USER_NOT_EXIST"),
    PASSWORD_WRONG("004","PASSWORD_WRONG"),
    USER_REGISTER_EXIST("006","USER_REGISTER_EXIST"),
    TOKEN_IN_VALID("007","TOKEN_IN_VALID"),
    TOKEN_IS_EMPTY("008","TOKEN_IS_EMPTY"),
    LIST_EMPTY("009","LIST_EMPTY"),
    OBJECT_EMPTY("010","OBJECT_EMPTY"),
    TICKET_IS_HOLDING("011","TICKET_IS_HOLDING"),
    TICKET_CREATED_BEFORE("012","TICKET_CREATED_BEFORE"),
    MOVIE_TIME_NOT_FOUND("013","MOVIE_TIME_NOT_FOUND");

    private String code;
    private String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }



}
