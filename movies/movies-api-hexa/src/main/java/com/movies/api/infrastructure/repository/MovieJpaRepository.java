package com.movies.api.infrastructure.repository;

import com.movies.api.infrastructure.repository.entity.MovieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<MovieJpaEntity, Long> {
    // Méthodes de requête personnalisées si nécessaire
    // List<MovieJpaEntity> findByHaveBeenSeen(boolean haveBeenSeen);
    // List<MovieJpaEntity> findByReleasedDateYearGreaterThan(int year);
}
