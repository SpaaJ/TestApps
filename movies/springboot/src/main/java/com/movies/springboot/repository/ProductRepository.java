package com.movies.springboot.repository;

import com.movies.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//CrudRepository + pagination and sorting
public interface ProductRepository extends JpaRepository<Product, Long> {
}
