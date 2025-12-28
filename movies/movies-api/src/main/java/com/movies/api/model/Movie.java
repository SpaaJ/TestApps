package com.movies.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String IMDbId;
    public String title;
    public String originalTitle;
    public int releasedDateYear;
    public int runtimeMinute ;
    public String story;
    public String imageUri;
    public boolean haveBeenSeen;
    public double RatingValue;
    public Double PersonalRatingValue ;
}