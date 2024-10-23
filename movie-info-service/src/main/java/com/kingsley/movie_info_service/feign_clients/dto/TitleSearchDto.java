package com.kingsley.movie_info_service.feign_clients.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"page"})
public record TitleSearchDto(int page, List<OttMovieDetail> results) {
}
