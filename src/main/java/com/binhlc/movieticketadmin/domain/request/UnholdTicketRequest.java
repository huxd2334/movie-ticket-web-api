package com.binhlc.movieticketadmin.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnholdTicketRequest extends BaseRequest{
    @JsonProperty("id_tran")
    Integer idTran;
    @Override
    public boolean isValidate() {
        if (idTran == null) {
            return false;
        }
        return true;
    }
}
