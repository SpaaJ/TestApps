package com.movies.api.application.port.in;

import com.movies.api.domain.model.Movie;
import java.util.List;

/**
 * Port d'entrée - Définit les cas d'utilisation de l'application
 */
public interface MovieUseCase {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie createMovie(Movie movie);
    Movie updateMovie(Long id, Movie movieDetails);
    void deleteMovie(Long id);
}
