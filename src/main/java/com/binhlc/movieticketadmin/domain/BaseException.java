package com.binhlc.movieticketadmin.domain;

import com.binhlc.movieticketadmin.common.ErrorType;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private ErrorType errorType;


}
