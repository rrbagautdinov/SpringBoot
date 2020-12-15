package ru.geekbrains.boot.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.shop.model.Product;
import ru.geekbrains.boot.shop.service.ProductService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public String allProducts(Model model) {
        model.addAttribute(productService.findAll());
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.update(product);
        return "redirect:/products/all";
    }
}
