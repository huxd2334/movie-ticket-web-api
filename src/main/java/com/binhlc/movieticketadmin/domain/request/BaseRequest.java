package com.binhlc.movieticketadmin.domain.request;

public abstract class BaseRequest {
    protected boolean isNullOrEmpty(String arg) {
        if (arg == null || arg.isEmpty()) {
            return true;
        }
        return false;
    }

    protected boolean isNull(Integer arg) {
        if (arg == null) {
            return true;
        }
        return false;
    }

    public abstract boolean isValidate();
}
