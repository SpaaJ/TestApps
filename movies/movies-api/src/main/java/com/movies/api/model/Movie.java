package com.movies.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imdbId;
    private String title;
    private String originalTitle;
    private int releasedDateYear;
    private int runtimeMinute ;
    private String story;
    private String imageUri;
    private boolean haveBeenSeen;
    private double ratingValue;
    private Double personalRatingValue ;
}