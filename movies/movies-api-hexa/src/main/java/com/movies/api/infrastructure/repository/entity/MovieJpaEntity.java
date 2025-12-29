package com.movies.api.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class MovieJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb_id", nullable = false, length = 20)
    private String imdbId;

    @Column(nullable = false)
    private String title;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "released_date_year", nullable = false)
    private int releasedDateYear;

    @Column(name = "runtime_minute", nullable = false)
    private int runtimeMinute;

    @Column(length = 5000)
    private String story;

    @Column(name = "image_uri")
    private String imageUri;

    @Column(name = "have_been_seen", nullable = false)
    private boolean haveBeenSeen;

    @Column(name = "rating_value", nullable = false)
    private double ratingValue;

    @Column(name = "personal_rating_value")
    private Double personalRatingValue;
}
