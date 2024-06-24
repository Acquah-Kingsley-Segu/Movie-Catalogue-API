package com.kingsley.movie_info_service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movie/catalogue")
public class MovieController {
    @GetMapping("/user/{id}/movies")
    public List<MovieEntity> getUserMovies(@PathVariable String id){
        return List.of(new MovieEntity("2847t2rsbua9fhxbvafydgqjn","DeadPool", "Marvel Animated Movie"), new MovieEntity("cbavhsvcjkwv.cv hcqyv ycvxb nzbcvc cx", "DeadPool 2", "Marvel Animated Movie"));
    }

    @PostMapping("/user/{id}/movie/create")
    public MovieDto createMovie(@PathVariable String id, @RequestBody MovieDto movieData){
        return movieData;
    }
}
