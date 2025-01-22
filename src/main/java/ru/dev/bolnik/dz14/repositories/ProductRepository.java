package ru.dev.bolnik.dz14.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.dev.bolnik.dz14.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void delete(Long id) {
        products.remove(findById(id));
    }

    public void update(Product product) {
        products.set(products.indexOf(findById(product.getId())), product);
    }

    public List<Product> findByTitleContaining(String titleFilter) {
        System.out.println(3);
        return products.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(titleFilter.toLowerCase()))
                .collect(Collectors.toList());
    }
}
