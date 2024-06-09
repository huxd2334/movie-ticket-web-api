package com.binhlc.movieticketadmin.controller.admin_web;

import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.MovieTimeRequest;
import com.binhlc.movieticketadmin.service.MovieTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/movie_time")
public class MovieTimeController {
    @Autowired
    private MovieTimeService movieTimeService;
    @PostMapping("/add")
    public Result add(@RequestBody MovieTimeRequest request){
        Result result = movieTimeService.addMovieTime(request);
        return result;
    }
}
