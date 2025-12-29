package com.movies.api.infrastructure.repository;

import com.movies.api.application.port.out.MovieRepositoryPort;
import com.movies.api.domain.model.Movie;
import com.movies.api.infrastructure.repository.mapper.MovieJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieRepositoryAdapter implements MovieRepositoryPort {

    private final MovieJpaRepository movieJpaRepository;
    private final MovieJpaMapper movieJpaMapper;

    @Override
    public List<Movie> findAll() {
        return movieJpaRepository.findAll().stream()
                .map(movieJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieJpaRepository.findById(id)
                .map(movieJpaMapper::toDomain);
    }

    @Override
    public Movie save(Movie movie) {
        var entity = movieJpaMapper.toEntity(movie);
        var savedEntity = movieJpaRepository.save(entity);
        return movieJpaMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        movieJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return movieJpaRepository.existsById(id);
    }
}
