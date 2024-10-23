package com.kingsley.movie_info_service;

import com.kingsley.movie_info_service.feign_clients.dto.OttAdvancedSearchResponse;
import com.kingsley.movie_info_service.feign_clients.ottdetails.SearchApiCalls;
import com.kingsley.movie_info_service.feign_clients.ottdetails.params.AdvancedSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieInfoService {
    private final SearchApiCalls searchApiCalls;
    public MovieInfoService(SearchApiCalls searchApiCalls) {
        this.searchApiCalls = searchApiCalls;
    }

    public OttAdvancedSearchResponse getMovies(AdvancedSearch params) {
        return searchApiCalls.homePageMovies(params);
    }

    public List<String> getMovieGenres(String genre) {
        return searchApiCalls.getSearchParam(genre);
    }

    public List<String> getMovieLanguages(String language) {
        return searchApiCalls.getSearchParam(language);
    }
}
