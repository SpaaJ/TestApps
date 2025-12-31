package com.movies.api.repository;

import com.movies.api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Component
@Repository
//CrudRepository + pagination and sorting
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
