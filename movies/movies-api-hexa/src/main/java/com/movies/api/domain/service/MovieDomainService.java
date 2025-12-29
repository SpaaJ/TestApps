package com.movies.api.domain.service;

import com.movies.api.domain.model.Movie;
import org.springframework.stereotype.Component;

/**
 * Service de domaine pour logique métier complexe
 * (optionnel si la logique reste simple)
 */
@Component
public class MovieDomainService {

    public boolean canBeRated(Movie movie) {
        // Logique métier : un film ne peut être noté que s'il a été vu
        return movie.isHaveBeenSeen();
    }

    public void validateMovieData(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (movie.getReleasedDateYear() < 1888) {
            throw new IllegalArgumentException("Invalid release year");
        }
    }
}
