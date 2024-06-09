package com.binhlc.movieticketadmin.controller.user_app;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.service.MovieTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/movie_time")
public class UserMovieTimeController extends BaseController {
    @Autowired
    private MovieTimeService movieTimeService;

    @GetMapping("/get")
    public Result getMovieTime(@RequestHeader String token,
                               @RequestParam Integer idMovie,
                               @RequestParam Integer date) {

        logger.info("[Movie Time User Get] Get time by idMovie {} and date {}", idMovie, date);
        if (token != null && idMovie != null && date != null) {
            Integer idUser = checkToken(token);
            if (idUser != null) {
                Result result = movieTimeService.getMovieTimeByIDandDate(idUser, token, idMovie, date);
                return result;
            } else {
                return Result.fail(ErrorType.TOKEN_IN_VALID);
            }
        } else {
            return Result.fail(ErrorType.ARGUMENT_NOT_VALID);
        }
    }
}
