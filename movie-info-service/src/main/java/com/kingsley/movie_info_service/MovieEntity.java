package com.kingsley.movie_info_service;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public MovieEntity(String userId, String name, String description) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public MovieEntity() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
