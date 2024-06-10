package com.binhlc.movieticketadmin.domain.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UpdateMovieRequest extends BaseRequest{
    private String name, director, cast, trailer, imageUrl, country, description, language, subTitles;
    private Integer releaseDate, duration, dubbing, status, censor;

    @Override
    public boolean isValidate() {
        if(isNullOrEmpty(name) || isNullOrEmpty(director) || isNullOrEmpty(cast) || isNullOrEmpty(trailer) || isNullOrEmpty(imageUrl) || isNullOrEmpty(country) || isNullOrEmpty(description) || isNullOrEmpty(language) || isNullOrEmpty(subTitles) || releaseDate == null || duration == null || dubbing == null || status == null || censor == null){
            return false;
        }
        return true;
    }
}
