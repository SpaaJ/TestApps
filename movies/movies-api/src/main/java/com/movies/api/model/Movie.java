package com.movies.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "IMDB ID is required")
    private String imdbId;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    private String originalTitle;

    @Min(value = 1888, message = "Year must be 1888 or later")
    @Max(value = 2100, message = "Year must be 2100 or earlier")
    private int releasedDateYear;

    @Min(value = 1, message = "Runtime must be at least 1 minute")
    private int runtimeMinute;

    @Size(max = 2000, message = "Story must not exceed 2000 characters")
    private String story;

    private String imageUri;

    // Changed to Boolean wrapper to handle null values from API
    private Boolean haveBeenSeen;

    @DecimalMin(value = "0.0", message = "Rating must be between 0 and 10")
    @DecimalMax(value = "10.0", message = "Rating must be between 0 and 10")
    private double ratingValue;

    @DecimalMin(value = "0.0", message = "Personal rating must be between 0 and 10")
    @DecimalMax(value = "10.0", message = "Personal rating must be between 0 and 10")
    private Double personalRatingValue;
}
