package com.binhlc.movieticketadmin.controller;

import com.binhlc.movieticketadmin.util.JWTutils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Integer checkToken(String token) {
        Map<String, String> map = JWTutils.validate(token);
        String id = map.get("user_id");
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            return null;
        }
    }
}
