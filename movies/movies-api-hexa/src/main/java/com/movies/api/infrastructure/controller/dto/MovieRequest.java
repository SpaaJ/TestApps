package com.movies.api.infrastructure.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    @NotBlank(message = "IMDb ID is required")
    @Pattern(regexp = "\\d{7,8}", message = "IMDb ID must be in format ttXXXXXXX")
    private String imdbId;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 255, message = "Original title must not exceed 255 characters")
    private String originalTitle;

    @Min(value = 1888, message = "Release year must be 1888 or later")
    @Max(value = 2100, message = "Release year must be 2100 or earlier")
    private int releasedDateYear;

    @Min(value = 1, message = "Runtime must be at least 1 minute")
    @Max(value = 1000, message = "Runtime must not exceed 1000 minutes")
    private int runtimeMinute;

    @Size(max = 5000, message = "Story must not exceed 5000 characters")
    private String story;

    private String imageUri;

    private boolean haveBeenSeen;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10.0")
    private double ratingValue;

    @DecimalMin(value = "0.0", message = "Personal rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Personal rating must not exceed 10.0")
    private Double personalRatingValue;
}
