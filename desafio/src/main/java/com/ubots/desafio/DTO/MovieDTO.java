package com.ubots.desafio.DTO;

import com.ubots.desafio.Models.Movie;

public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private boolean reviewed;
    private int rating;

    public MovieDTO() {}

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.reviewed = movie.isReviewed();
        this.rating = movie.getRating();
    }

    public Movie toEntity() {
        Movie movie = new Movie();
        movie.setId(this.id);
        movie.setTitle(this.title);
        movie.setDescription(this.description);
        movie.setReviewed(this.reviewed);
        movie.setRating(this.rating);
        return movie;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
