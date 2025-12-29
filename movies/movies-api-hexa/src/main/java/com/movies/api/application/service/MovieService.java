package com.movies.api.application.service;

import com.movies.api.application.port.in.MovieUseCase;
import com.movies.api.application.port.out.MovieRepositoryPort;
import com.movies.api.domain.exception.MovieNotFoundException;
import com.movies.api.domain.model.Movie;
import com.movies.api.domain.service.MovieDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service applicatif - Orchestre les cas d'utilisation
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MovieService implements MovieUseCase {

    private final MovieRepositoryPort movieRepositoryPort;
    private final MovieDomainService movieDomainService;

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        log.debug("Fetching all movies");
        return movieRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Movie getMovieById(Long id) {
        log.debug("Fetching movie with id: {}", id);
        return movieRepositoryPort.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public Movie createMovie(Movie movie) {
        log.debug("Creating new movie: {}", movie.getTitle());

        // Validation métier
        movieDomainService.validateMovieData(movie);

        // Création sans ID
        Movie newMovie = Movie.builder()
                .imdbId(movie.getImdbId())
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .releasedDateYear(movie.getReleasedDateYear())
                .runtimeMinute(movie.getRuntimeMinute())
                .story(movie.getStory())
                .imageUri(movie.getImageUri())
                .haveBeenSeen(movie.isHaveBeenSeen())
                .ratingValue(movie.getRatingValue())
                .personalRatingValue(movie.getPersonalRatingValue())
                .build();

        Movie savedMovie = movieRepositoryPort.save(newMovie);
        log.info("Movie created with id: {}", savedMovie.getId());
        return savedMovie;
    }

    @Override
    public Movie updateMovie(Long id, Movie movieDetails) {
        log.debug("Updating movie with id: {}", id);

        // Vérifier que le film existe
        Movie existingMovie = getMovieById(id);

        // Validation métier
        movieDomainService.validateMovieData(movieDetails);

        // Mise à jour
        Movie updatedMovie = Movie.builder()
                .id(existingMovie.getId())
                .imdbId(movieDetails.getImdbId())
                .title(movieDetails.getTitle())
                .originalTitle(movieDetails.getOriginalTitle())
                .releasedDateYear(movieDetails.getReleasedDateYear())
                .runtimeMinute(movieDetails.getRuntimeMinute())
                .story(movieDetails.getStory())
                .imageUri(movieDetails.getImageUri())
                .haveBeenSeen(movieDetails.isHaveBeenSeen())
                .ratingValue(movieDetails.getRatingValue())
                .personalRatingValue(movieDetails.getPersonalRatingValue())
                .build();

        Movie savedMovie = movieRepositoryPort.save(updatedMovie);
        log.info("Movie updated with id: {}", savedMovie.getId());
        return savedMovie;
    }

    @Override
    public void deleteMovie(Long id) {
        log.debug("Deleting movie with id: {}", id);

        if (!movieRepositoryPort.existsById(id)) {
            throw new MovieNotFoundException(id);
        }

        movieRepositoryPort.deleteById(id);
        log.info("Movie deleted with id: {}", id);
    }
}
