package com.binhlc.movieticketadmin.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    String name;

    @Override
    public String toString() {
        return "CategoryRequest{name='" + name + "'}";
    }
}
