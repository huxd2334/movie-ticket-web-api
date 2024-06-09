package com.binhlc.movieticketadmin.controller.user_app;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/movie")
public class MovieController extends BaseController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/getTop")
    public Result getTop() {
        logger.info("[MovieController] Get Top Movie Controller");
        Result result = movieService.getTopMovie();
        logger.info("[MovieController] Get Top Movie result: " + result);
        if (result != null) {
            return result;
        }
        return Result.fail(ErrorType.SYSTEM_ERROR);
    }

    @GetMapping("/getIsShowing")
    public Result getIsShowing(@RequestParam("partdate") int partDate,
                               @RequestParam("start") int start,
                               @RequestParam("limit") int limit) {
        logger.info("[MovieController] Get Is Showing Movie Controller");
        Result result = movieService.getShowingNowMovie(partDate, start, limit);
        logger.info("[MovieController] Get Is Showing Movie result: " + result);
        if (result != null) {
            return result;
        }
        return Result.fail(ErrorType.SYSTEM_ERROR);
    }

    @GetMapping("/getComingSoon")
    public Result getCommingSoon(@RequestParam("partdate") int partDate,
                                 @RequestParam("start") int start,
                                 @RequestParam("limit") int limit) {
        logger.info("[MovieController] Get Coming Soon Movie Controller", partDate, start, limit);
        Result result = movieService.getComingSoonMoive(partDate, start, limit);
        logger.info("[MovieController] Get Coming Soon Movie result: " + result);
        if (result != null) {
            return result;
        }
        return Result.fail(ErrorType.SYSTEM_ERROR);
    }

    @GetMapping("/getDetail")
    public Result getDetail(@RequestParam("id") int idMovie) {
        logger.info("[MovieController] Get Movie Detail Controller", idMovie);
        Result result = movieService.getMovieById(idMovie);
        logger.info("[MovieController] Get Movie Detail result: " + result);
        if (result != null) {
            return result;
        }
        return Result.fail(ErrorType.SYSTEM_ERROR);
    }
}
