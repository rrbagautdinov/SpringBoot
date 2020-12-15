package ru.geekbrains.boot.shop.exception;

public class ProductAlreadyAddedException extends RuntimeException {
    public ProductAlreadyAddedException(String message) {
        super(message);
    }
}
