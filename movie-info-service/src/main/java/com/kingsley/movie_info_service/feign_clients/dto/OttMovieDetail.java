package com.kingsley.movie_info_service.feign_clients.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OttMovieDetail(String imdbid, String title, String imdbrating, Integer released, String type, String synopsis, List<String> imageurl, List<String> genre) {
}
