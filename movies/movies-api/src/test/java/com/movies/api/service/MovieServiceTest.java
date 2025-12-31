package com.movies.api.service;

import com.movies.api.exception.MovieNotFoundException;
import com.movies.api.model.Movie;
import com.movies.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    // Méthode helper
    private Movie createMovie(Long id, String title) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        return movie;
    }

    @Test
    void whenFindById_thenReturnMovie() {
        // ARRANGE - Préparer les données de test
        Long movieId = 1L;
//        Movie movie = new Movie();
//        movie.setId(movieId);
//        movie.setTitle("Inception");
//        movie.setHaveBeenSeen(true);

        Movie movie = createMovie(movieId, "Inception");
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

    @Test
    void whenMovieNotFound_thenThrowException() {
        // ARRANGE
        Long movieId = 999L;

        when(movieRepository.findById(movieId))
                .thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(MovieNotFoundException.class, () -> {
            movieService.getMovieById(movieId);
        });

        verify(movieRepository).findById(movieId);
    }

    @Test
    void testGetAllMovies() {
        // Créer des données de test
        List<Movie> movies = Arrays.asList(
                createMovie(1L, "Inception"),
                createMovie(2L, "Interstellar")
        );

        // Simuler le comportement
        when(movieRepository.findAll()).thenReturn(movies);

        // Tester
        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());
        verify(movieRepository).findAll();
    }



    @Test
    void whenDatabaseError_thenThrowException() {
        when(movieRepository.findAll())
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            movieService.getAllMovies();
        });
    }

    @Test
    void testDeleteMovie() {
        Long movieId = 1L;
        Movie movie = createMovie(movieId, "Test Movie");

        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(movie));

        // Simuler la suppression (void method)
        doNothing().when(movieRepository).deleteById(movieId);

        movieService.deleteMovie(movieId);

        // Vérifications multiples
        verify(movieRepository).findById(movieId);
        verify(movieRepository).deleteById(movieId);
        verify(movieRepository, times(1)).deleteById(movieId);
        verify(movieRepository, never()).save(any());
    }

    @Test
    void testCreateMovie() {
        Movie newMovie = createMovie(null, "New Movie");
        Movie savedMovie = createMovie(1L, "New Movie");

        // Accepte n'importe quel objet Movie
        when(movieRepository.save(any(Movie.class)))
                .thenReturn(savedMovie);

        Movie result = movieService.createMovie(newMovie);

        assertNotNull(result.getId());
        assertEquals("New Movie", result.getTitle());

        // Vérifier qu'un Movie a été sauvegardé
        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void testUpdateMovie_captureArgument() {
        Long movieId = 1L;
        Movie existingMovie = createMovie(movieId, "Old Title");
        Movie updateData = createMovie(null, "New Title");

        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(any(Movie.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Créer un captor
        ArgumentCaptor<Movie> movieCaptor = ArgumentCaptor.forClass(Movie.class);

        movieService.updateMovie(movieId, updateData);

        // Capturer l'argument passé à save()
        verify(movieRepository).save(movieCaptor.capture());

        // Vérifier les valeurs capturées
        Movie capturedMovie = movieCaptor.getValue();
        assertEquals(movieId, capturedMovie.getId());
        assertEquals("New Title", capturedMovie.getTitle());
    }

    @Test
    void testCustomBehavior() {
        when(movieRepository.save(any(Movie.class)))
                .thenAnswer(invocation -> {
                    Movie movie = invocation.getArgument(0);
                    movie.setId(100L); // Simuler l'ID généré
                    return movie;
                });

        Movie newMovie = createMovie(null, "Test");
        Movie result = movieService.createMovie(newMovie);

        assertEquals(100L, result.getId());
    }

    @Test
    void whenDeleteExistingMovie_thenSuccessfullyDeleted() {
        // Given
        Long movieId = 1L;
        Movie movie = createMovie(movieId, "Test Movie");

        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(movie));

        // When
        movieService.deleteMovie(movieId);

        // Then
        verify(movieRepository).findById(movieId);
        verify(movieRepository).deleteById(movieId);
    }

    @Test
    void whenDeleteNonExistingMovie_thenThrowException() {
        // Given
        Long invalidId = 999L;

        when(movieRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            movieService.deleteMovie(invalidId);
        });

        // Then
        verify(movieRepository).findById(invalidId);Z
        verify(movieRepository, never()).deleteById(any());
    }

}
