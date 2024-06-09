package com.binhlc.movieticketadmin.domain.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddRoomRequest {
    private String name;
    private int row, col;
}
