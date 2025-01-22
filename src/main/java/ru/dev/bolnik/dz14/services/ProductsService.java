package ru.dev.bolnik.dz14.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dev.bolnik.dz14.entities.Product;
import ru.dev.bolnik.dz14.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductsService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public List<Product> findByTitleContaining(String titleFilter) {
        System.out.println(2);
        if (titleFilter == null || titleFilter.isEmpty()) {
            System.out.println(5);
            return productRepository.findAll();
        } else {
            return productRepository.findByTitleContaining(titleFilter);
        }
    }
}
