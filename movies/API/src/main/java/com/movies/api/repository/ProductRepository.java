package com.movies.api.repository;

import com.movies.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//CrudRepository + pagination and sorting
public interface ProductRepository extends JpaRepository<Product, Long> {
}
