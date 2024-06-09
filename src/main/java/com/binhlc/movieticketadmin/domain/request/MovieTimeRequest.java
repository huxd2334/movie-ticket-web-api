package com.binhlc.movieticketadmin.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class MovieTimeRequest {
    @JsonProperty("id_movie")
    private int idMovie;
    @JsonProperty("id_room")
    private int idRoom;
    private int price;
    private int time;
    private int date;
}
