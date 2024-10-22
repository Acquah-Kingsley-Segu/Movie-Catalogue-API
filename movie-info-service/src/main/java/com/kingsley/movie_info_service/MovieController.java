package com.kingsley.movie_info_service;

import com.kingsley.movie_info_service.feign_clients.dto.OttAdvancedSearchResponse;
import com.kingsley.movie_info_service.feign_clients.ottdetails.params.AdvancedSearch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/xclusive")
public class MovieController {
    private final MovieInfoService movieInfoService;

    public MovieController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/movies")
    public OttAdvancedSearchResponse homeMovies(){
        return movieInfoService.getMovies(new AdvancedSearch());
    }

//    public OttAdvancedSearchResponse advancedSearch(@RequestParam(required = false) String start_year,
//                                                @RequestParam(required = false) String end_year,
//                                                @RequestParam(required = false) String min_imdb,
//                                                @RequestParam(required = false) String max_imdb,
//                                                @RequestParam(required = false) String genre,
//                                                @RequestParam(required = false) String language,
//                                                @RequestParam(required = false) String type,
//                                                @RequestParam(required = false) String sort,
//                                                @RequestParam(required = false) String page){
//        return movieInfoService.getMovies(new AdvancedSearch(start_year, end_year, min_imdb, max_imdb, genre, language, type, sort, page));
//    }

    @GetMapping("/user/{id}/movies")
    public List<MovieEntity> getUserMovies(@PathVariable String id){
        return List.of(new MovieEntity("2847t2rsbua9fhxbvafydgqjn","DeadPool", "Marvel Animated Movie"), new MovieEntity("cbavhsvcjkwv.cv hcqyv ycvxb nzbcvc cx", "DeadPool 2", "Marvel Animated Movie"));
    }

    @PostMapping("/user/{id}/movie/create")
    public MovieDto createMovie(@PathVariable String id, @RequestBody MovieDto movieData){
        return movieData;
    }
}
