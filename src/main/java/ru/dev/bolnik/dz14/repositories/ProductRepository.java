package ru.dev.bolnik.dz14.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.bolnik.dz14.entities.Product;

import java.util.List;

@Repository


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContaining(String title);
}
