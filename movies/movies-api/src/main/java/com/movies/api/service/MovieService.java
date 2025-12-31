package com.movies.api.service;

import com.movies.api.exception.MovieNotFoundException;
import com.movies.api.model.Movie;
import com.movies.api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie createMovie(Movie movie) {
        movie.setId(null); // Ensure we're creating, not updating
        // Set default for haveBeenSeen if null
        if (movie.getHaveBeenSeen() == null) {
            movie.setHaveBeenSeen(false);
        }
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = getMovieById(id);
        movie.setImdbId(movieDetails.getImdbId());
        movie.setTitle(movieDetails.getTitle());
        movie.setOriginalTitle(movieDetails.getOriginalTitle());
        movie.setReleasedDateYear(movieDetails.getReleasedDateYear());
        movie.setRuntimeMinute(movieDetails.getRuntimeMinute());
        movie.setStory(movieDetails.getStory());
        movie.setImageUri(movieDetails.getImageUri());
        movie.setHaveBeenSeen(movieDetails.getHaveBeenSeen()); // Now works!
        movie.setRatingValue(movieDetails.getRatingValue());
        movie.setPersonalRatingValue(movieDetails.getPersonalRatingValue());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        // Verify movie exists before deleting
        getMovieById(id);
        movieRepository.deleteById(id);
    }
}
