package com.kingsley.movie_info_service;

import com.kingsley.movie_info_service.feign_clients.dto.OttAdvancedSearchResponse;
import com.kingsley.movie_info_service.feign_clients.ottdetails.SearchApiCalls;
import com.kingsley.movie_info_service.feign_clients.ottdetails.params.AdvancedSearch;
import org.springframework.stereotype.Service;

@Service
public class MovieInfoService {
    private final SearchApiCalls searchApiCalls;
    public MovieInfoService(SearchApiCalls searchApiCalls) {
        this.searchApiCalls = searchApiCalls;
    }

    public OttAdvancedSearchResponse getMovies(AdvancedSearch params) {
        return searchApiCalls.homePageMovies(params);
    }
}
