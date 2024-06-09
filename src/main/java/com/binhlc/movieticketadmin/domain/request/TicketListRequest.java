package com.binhlc.movieticketadmin.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketListRequest extends BaseRequest{
    @JsonProperty("list")
    List<Integer> listId;
    @Override
    public boolean isValidate() {
       if(listId == null || listId.size() == 0){
           return false;
       }
       return true;
    }
}
