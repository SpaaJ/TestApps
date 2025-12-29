package com.movies.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entité métier pure - Aucune dépendance technique
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
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

    // Logique métier pure
    public boolean hasPersonalRating() {
        return personalRatingValue != null;
    }

    public boolean isRecentMovie() {
        return releasedDateYear >= 2020;
    }

    public boolean isLongMovie() {
        return runtimeMinute > 180;
    }
}
