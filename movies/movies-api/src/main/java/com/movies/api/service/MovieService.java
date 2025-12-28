package com.movies.api.service;

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
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie createMovie(Movie movie) {
        movie.setId(null);
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = getMovieById(id);
        movie.setTitle(movieDetails.getTitle());
        movie.setReleasedDateYear(movieDetails.getReleasedDateYear());
        movie.setStory(movieDetails.getStory());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}