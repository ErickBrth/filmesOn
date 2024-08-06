package com.ubots.desafio.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MovieDTO {

    @JsonIgnore
    private Long id;
    private String title;
    private String description;
    private boolean reviewed;

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
}