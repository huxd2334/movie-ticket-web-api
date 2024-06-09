package com.binhlc.movieticketadmin.service;

import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.logging.Logger;

public class BaseService {
    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Timestamp currrentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }
}
