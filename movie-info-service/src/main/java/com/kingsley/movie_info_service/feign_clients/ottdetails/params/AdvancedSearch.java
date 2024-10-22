package com.kingsley.movie_info_service.feign_clients.ottdetails.params;

public class AdvancedSearch{
    private String start_year;
    private String end_year;
    private String min_imdb;
    private String max_imdb;
    private String genre;
    private String language;
    private String type;
    private String sort;
    private String page;

    public AdvancedSearch() {}
    public AdvancedSearch(String start_year, String end_year, String min_imdb,
                          String max_imdb, String genre, String language,
                          String type, String sort, String page) {
        this.start_year = start_year;
        this.end_year = end_year;
        this.min_imdb = min_imdb;
        this.max_imdb = max_imdb;
        this.genre = genre;
        this.language = language;
        this.type = type;
        this.sort = sort;
        this.page = page;
    }

    public String getStart_year() {
        return start_year;
    }

    public String getEnd_year() {
        return end_year;
    }

    public String getMin_imdb() {
        return min_imdb;
    }

    public String getMax_imdb() {
        return max_imdb;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getType() {
        return type;
    }

    public String getSort() {
        return sort;
    }

    public String getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "AdvancedSearch{" +
                "start_year=" + start_year +
                ", end_year=" + end_year +
                ", min_imdb=" + min_imdb +
                ", max_imdb=" + max_imdb +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", sort='" + sort + '\'' +
                ", page=" + page +
                '}';
    }
}
