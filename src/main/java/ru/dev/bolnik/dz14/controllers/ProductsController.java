package ru.dev.bolnik.dz14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dev.bolnik.dz14.entities.Product;
import ru.dev.bolnik.dz14.services.ProductsService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    public String showProductsList(@RequestParam(required = false) String titleFilter, Model model) {
        List<Product> products;
        if (titleFilter != null && !titleFilter.isEmpty()) {
            System.out.println(1);
            products = productsService.findByTitleContaining(titleFilter);
        } else {
            System.out.println(2);
            products = productsService.getAllProducts();
        }
        Product product = new Product();
        model.addAttribute("products", products);
        model.addAttribute("titleFilter", titleFilter);
        model.addAttribute("product", product);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productsService.delete(id);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        Product product = productsService.getById(id);
        if (product == null) {
            model.addAttribute("error", "Продукт не найден");
            return "error";
        }
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, @ModelAttribute Product product) {
        productsService.update(product);
        return "redirect:/products";
    }


}
