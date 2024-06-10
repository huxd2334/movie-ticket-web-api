package com.binhlc.movieticketadmin.controller.admin_web;

import com.binhlc.movieticketadmin.controller.BaseController;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.AddMovieRequest;
import com.binhlc.movieticketadmin.domain.request.UpdateMovieRequest;
import com.binhlc.movieticketadmin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/movie")
public class AdminMovieController extends BaseController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public Result addMovie(@RequestBody AddMovieRequest request) {
        Result result = movieService.add(request);
        return result;
    }

    @PutMapping("/update/{id}")
    public Result updateMovie(@PathVariable Integer id, @RequestBody UpdateMovieRequest request) {
        Result result = movieService.updateMovie(id, request);
        return result;
    }


}
