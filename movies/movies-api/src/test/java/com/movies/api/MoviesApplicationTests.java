package com.movies.api;

import com.movies.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataJpaTest
class MoviesApplicationTests {

	@Autowired
	MovieRepository productRepository;

	@Test
	void testAllMoviesSize() {
		var allProducts = productRepository.findAll();
		System.out.println("allProducts size : " + allProducts.size());
		assertThat(allProducts.size()).isEqualTo(5);
	}

}
