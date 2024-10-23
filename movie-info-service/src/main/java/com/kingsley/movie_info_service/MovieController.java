package com.kingsley.movie_info_service;

import com.kingsley.movie_info_service.feign_clients.dto.OttAdvancedSearchResponse;
import com.kingsley.movie_info_service.feign_clients.dto.TitleSearchDto;
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
    public OttAdvancedSearchResponse homeMovies(@RequestParam(required = false) String start_year,
                                                @RequestParam(required = false) String end_year,
                                                @RequestParam(required = false) String min_imdb,
                                                @RequestParam(required = false) String max_imdb,
                                                @RequestParam(required = false) String genre,
                                                @RequestParam(required = false) String language,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) String sort,
                                                @RequestParam(required = false) String page){
        return movieInfoService.getMovies(new AdvancedSearch(start_year, end_year, min_imdb, max_imdb, genre, language, type, sort, page));
    }

    @GetMapping("/movies/genres")
    public List<String> getMovieGenres(){
        return movieInfoService.getMovieGenres("genre");
    }

    @GetMapping("/movies/languages")
    public List<String> getMovieLanguage(){
        return movieInfoService.getMovieLanguages("language");
    }

    @GetMapping("/movies/search/{title}")
    public TitleSearchDto searchMovies(@PathVariable String title){
        return movieInfoService.searchMoviesByTitle(title);
    }
}
