package com.kingsley.movie_info_service.feign_clients.dto;

import java.util.List;

public record OttMovieDetail(String imdbid, String title, String imdbrating, Integer released, String type, String synopsis, List<String> imageurl, List<String> genre) {
}
