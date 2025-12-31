package com.movies.api.service;

import com.movies.api.model.Movie;
import com.movies.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void whenFindById_thenReturnMovie() {
        // ARRANGE - Préparer les données de test
        Long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Inception");
        movie.setHaveBeenSeen(true);

        // Configurer le comportement du mock
        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(movie));

        // ACT - Exécuter la méthode testée
        Movie result = movieService.getMovieById(movieId);

        // ASSERT - Vérifier les résultats
        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
        assertEquals(true, result.getHaveBeenSeen());

        // Vérifier que le repository a été appelé
        verify(movieRepository, times(1)).findById(movieId);
    }
}
