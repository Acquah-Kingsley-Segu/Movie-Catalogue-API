package com.kingsley.movie_info_service.feign_clients.dto;

import java.util.List;

public record OttAdvancedSearchResponse(int page, List<OttMovieDetail> results){
}
