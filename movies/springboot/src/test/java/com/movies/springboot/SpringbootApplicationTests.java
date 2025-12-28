package com.movies.springboot;

import com.movies.springboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
//@DataJpaTest
class SpringbootApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void testAllProductsSize() {
		var allProducts = productRepository.findAll();
		System.out.println("allProducts size : " + allProducts.size());
		assertThat(allProducts.size()).isEqualTo(5);
	}

}
