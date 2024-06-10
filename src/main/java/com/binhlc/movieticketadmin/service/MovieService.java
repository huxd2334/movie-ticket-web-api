package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.common.ErrorType;
import com.binhlc.movieticketadmin.domain.entity.CategoryEntity;
import com.binhlc.movieticketadmin.domain.entity.MovieCategoryEntity;
import com.binhlc.movieticketadmin.domain.entity.MovieEntity;
import com.binhlc.movieticketadmin.domain.repo.CategoryRepo;
import com.binhlc.movieticketadmin.domain.repo.MovieCategoryRepo;
import com.binhlc.movieticketadmin.domain.repo.MovieRepo;
import com.binhlc.movieticketadmin.domain.reponse.MovieResponse;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.AddMovieRequest;
import com.binhlc.movieticketadmin.domain.request.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService extends BaseService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private MovieCategoryRepo movieCategoryRepo;

    public int saveMovie(MovieEntity movieEntity) {
        try{
            movieRepo.save(movieEntity);
            return  1;
        }catch (Exception e){
            System.err.println("[Movie Service] Adding Error: " + e.getMessage());
            return 0;
        }
    }

    public Result updateMovie(Integer id, UpdateMovieRequest request){
        Optional<MovieEntity> optional = movieRepo.findById(id);
        if(optional.isPresent()){
            MovieEntity movie = optional.get();
            movie.setName(request.getName());
            movie.setPartTime(request.getReleaseDate());
            movie.setOld(request.getCensor());
            movie.setActor(request.getCast());
            movie.setDirector(request.getDirector());
            movie.setCountry(request.getCountry());
            movie.setLanguage(request.getLanguage());
            movie.setTime(request.getDuration());
            movie.setContain(request.getDescription());
            movie.setTrailer(request.getTrailer());
            movie.setImageUrl(request.getImageUrl());
            movie.setUpdateBy(1);
            movie.setTimeUpdate(currrentTime());
            movie.setStatus(request.getStatus());
            movie.setSubTitle(request.getSubTitles());
            movie.setCreateBy(1);
            movie.setDubbing(request.getDubbing());
            return saveMovie(movie) == 1 ? Result.success() : Result.fail();
        }
        return Result.fail(ErrorType.MOVIE_NOT_FOUND);
    }

    public Result add(AddMovieRequest request){
        MovieEntity movie = new MovieEntity();
        movie.setName(request.getName());
        movie.setPartTime(request.getReleasedDate());
        movie.setOld(request.getCensor());
        movie.setActor(request.getActor());
        movie.setDirector(request.getDirector());
        movie.setCountry(request.getCountry());
        movie.setLanguage(request.getLanguage());
        movie.setTime(request.getDuration());
        movie.setContain(request.getStoryLine());
        movie.setTrailer(request.getTrailer());
        movie.setImageUrl(request.getImageUrl());
        movie.setCreateBy(1);
        movie.setUpdateBy(1);
        movie.setTimeCreate(currrentTime());
        movie.setTimeUpdate(currrentTime());
        movie.setStatus(1);
        movie.setSubTitle(request.getSubTitle());
        movie.setCreateBy(1);
        movie.setDubbing(request.getDubbing());
        movie.setUpdateBy(1);
        movie.setView(1);
        return saveMovie(movie) == 1 ? Result.success() : Result.fail();
    }

    public Result getTopMovie(){
        List<MovieEntity> list = movieRepo.getTopView();
        List<MovieResponse> listResponse = getListMovieResponse(list);
        if(listResponse!=null)
            return Result.success(listResponse);
        return Result.fail(ErrorType.LIST_EMPTY);
    }

    public Result getShowingNowMovie(int date, int start, int limit){
        List<MovieEntity> list = movieRepo.getListMovieIsShowing(date,start,limit);
        List<MovieResponse> listResponse = getListMovieResponse(list);
        if(listResponse!=null)
            return Result.success(listResponse);
        return Result.fail(ErrorType.LIST_EMPTY);
    }

    public Result getComingSoonMoive(int date, int start, int limit){
        List<MovieEntity> list = movieRepo.getListMovieCommingSoon(date,start,limit);
        List<MovieResponse> listResponse = getListMovieResponse(list);
        if(listResponse!=null)
            return Result.success(listResponse);
        return Result.fail(ErrorType.LIST_EMPTY);
    }

    private List<MovieResponse> getListMovieResponse(List<MovieEntity> list) {
        List<MovieResponse> listResponse = new ArrayList<>();
        if(list!=null){
            for(MovieEntity movie : list){
                MovieResponse response = new MovieResponse();
                response.setMovie(movie);
                List<MovieCategoryEntity> listMovieCategory = movieCategoryRepo.findAllByIdMovie(movie.getId());
                if(listMovieCategory!=null){
                    List<CategoryEntity> listCate = new ArrayList<>();
                    for(MovieCategoryEntity movieCategory : listMovieCategory){
                        Optional<CategoryEntity> optional = categoryRepo.findById(movieCategory.getIdCategory());
                        if(optional.isPresent()){
                            listCate.add(optional.get());
                        }
                    }
                    response.setCategories(listCate);
                }
                listResponse.add(response);
            }
            return listResponse;
        }
        return null;
    }

    public Result getMovieById(int id){
        Optional<MovieEntity> movieEntity = movieRepo.findById(id);
        if(movieEntity.isPresent()){
            MovieResponse response = new MovieResponse();
            MovieEntity entity = movieEntity.get();
            long view =  entity.getView();
            view = view + 1;
            entity.setView(view);
            movieRepo.save(entity);
            response.setMovie(entity);
            List<MovieCategoryEntity> listMovieCategory = movieCategoryRepo.findAllByIdMovie(movieEntity.get().getId());
            if(listMovieCategory!=null){
                List<CategoryEntity> listCate = new ArrayList<>();
                for(MovieCategoryEntity movieCategory : listMovieCategory){
                    Optional<CategoryEntity> optional = categoryRepo.findById(movieCategory.getIdCategory());
                    if(optional.isPresent()){
                        listCate.add(optional.get());
                    }
                }
                response.setCategories(listCate);
                return Result.success(response);
            }
        }
        return null;
    }

}
