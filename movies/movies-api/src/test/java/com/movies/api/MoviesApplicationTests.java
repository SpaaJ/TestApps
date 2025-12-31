package com.movies.api;

import com.movies.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataJpaTest
class MoviesApplicationTests {

	@Autowired
	MovieRepository movieRepository;

	@Test
	void testAllMoviesSize() {
		var allMovies = movieRepository.findAll();
		System.out.println("allMovies size : " + allMovies.size());
		assertThat(allMovies.size()).isEqualTo(3);
	}

}
