package com.binhlc.movieticketadmin.domain.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddMovieRequest {
    private String name;
    private int duration, censor, releasedDate, dubbing;
    private String director, actor, storyLine, imageUrl, country, language, subTitle, trailer;
}
