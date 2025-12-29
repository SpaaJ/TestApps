package com.movies.api.application.port.out;

import com.movies.api.domain.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 * Port de sortie - Interface pour la persistence
 */
public interface MovieRepositoryPort {
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    Movie save(Movie movie);
    void deleteById(Long id);
    boolean existsById(Long id);
}
