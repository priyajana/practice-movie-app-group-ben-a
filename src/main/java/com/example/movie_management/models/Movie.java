package com.example.movie_management.models;

import java.util.Objects;

public class Movie {
    private static int nextId = 1;

    private final int id;
    private String title;
    private int rating;
    private String description;

    public Movie(String title, Integer rating, String description) {
        this.id = nextId;
        this.title = title;
        this.rating = rating;
        this.description = description;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title + " (" + rating + ")" + " --- Summary: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
