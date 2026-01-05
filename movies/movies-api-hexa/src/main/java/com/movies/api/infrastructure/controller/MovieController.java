package com.movies.api.infrastructure.controller;

import com.movies.api.application.port.in.MovieUseCase;
import com.movies.api.infrastructure.controller.dto.MovieRequest;
import com.movies.api.infrastructure.controller.dto.MovieResponse;
import com.movies.api.infrastructure.controller.mapper.MovieDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieUseCase movieUseCase;
    private final MovieDtoMapper movieDtoMapper;

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieUseCase.getAllMovies().stream()
                .map(movieDtoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable Long id) {
        return movieDtoMapper.toResponse(movieUseCase.getMovieById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse createMovie(@Valid @RequestBody MovieRequest request) {
        return movieDtoMapper.toResponse(
                movieUseCase.createMovie(movieDtoMapper.toDomain(request))
        );
    }

    @PutMapping("/{id}")
    public MovieResponse updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody MovieRequest request) {
        return movieDtoMapper.toResponse(
                movieUseCase.updateMovie(id, movieDtoMapper.toDomain(request))
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieUseCase.deleteMovie(id);
    }
}
