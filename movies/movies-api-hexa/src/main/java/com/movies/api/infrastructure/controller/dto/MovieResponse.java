package com.movies.api.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String imdbId;
    private String title;
    private String originalTitle;
    private int releasedDateYear;
    private int runtimeMinute;
    private String story;
    private String imageUri;
    private boolean haveBeenSeen;
    private double ratingValue;
    private Double personalRatingValue;
}
