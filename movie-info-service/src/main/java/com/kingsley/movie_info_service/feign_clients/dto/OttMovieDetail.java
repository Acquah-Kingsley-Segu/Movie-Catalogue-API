package com.kingsley.movie_info_service.feign_clients.dto;

import java.util.List;

public record OttMovieDetail(String imdbId, String title, String imdbRating, Integer released, String type, String synopsis, List<String> imageUrl, List<String> genre) {
}
