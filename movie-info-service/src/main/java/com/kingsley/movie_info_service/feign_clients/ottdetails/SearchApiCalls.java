package com.kingsley.movie_info_service.feign_clients.ottdetails;

import com.kingsley.movie_info_service.feign_clients.config.OttFeignConfig;
import com.kingsley.movie_info_service.feign_clients.dto.OttAdvancedSearchResponse;
import com.kingsley.movie_info_service.feign_clients.ottdetails.params.AdvancedSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ott-search-endpoints", url = "${OTT_MOVIES_BASEURL}", configuration = OttFeignConfig.class)
public interface SearchApiCalls {
    @GetMapping(name = "advanced_search", path = "/advancedsearch")
    OttAdvancedSearchResponse homePageMovies(@SpringQueryMap AdvancedSearch advancedSearch);
}
