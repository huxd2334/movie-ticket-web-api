package com.binhlc.movieticketadmin.domain.reponse;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.BaseException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Schema(description = "Response Structure")
public class Result<T> {
    public static final String SUCCESSFUL_CODE = "200";
    public static final String SUCCESSFUL_MESG = "OK";
    @Schema(description = "CODE ID", required = true)
    private String code;
    @Schema(description = "Description")
    private String message;
    @Schema(description = "Time")
    private Instant timestamp;
    @Schema(description = "Data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
        this.timestamp = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.timestamp = ZonedDateTime.now().toInstant();
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    public static Result success() {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    public static Result fail() {
        return new Result(ErrorType.SYSTEM_ERROR);
    }


    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }


    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    public static Result fail(String code, String msg, Object data) {
        return new Result<>(code, msg, data);
    }


    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }


    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }


    public static Result fail(Object data) {
        return new Result<>(ErrorType.SYSTEM_ERROR, data);
    }
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }



}
