package ru.geekbrains.boot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.boot.shop.exception.ProductAlreadyAddedException;
import ru.geekbrains.boot.shop.exception.ProductNotFoundException;
import ru.geekbrains.boot.shop.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Колбаса", 99));
        productList.add(new Product(2L, "Сосиски", 129));
        productList.add(new Product(3L, "Карбонад", 299));
        productList.add(new Product(4L, "Сардельки", 199));
        productList.add(new Product(5L, "Ветчина", 159));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(productList);
    }

    public Product save(Product product) {
        if (product.getId() != null) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId().equals(product.getId())) {
                    throw new ProductAlreadyAddedException("Продукт с ID: " + product.getId() + " уже существует");
                }
            }
        }
        productList.add(product);
        return product;
    }

    public Product update(Product product) {
        if (product.getId() != null) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId().equals(product.getId())) {
                    productList.set(i, product);
                    return product;
                }
            }
        }
        throw new ProductNotFoundException("Продукт с ID: " + product.getId() + " не найден");
    }

    public void deleteById(Long id) {
        productList.removeIf(product -> product.getId().equals(id));
    }
}
