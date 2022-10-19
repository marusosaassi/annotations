package org.marusosa.annotations.example;

import org.marusosa.annotations.example.models.Product;

import java.time.LocalDate;

public class AnnotationExample {
    public static void main(String[] args) {
        Product product = new Product();
        product.setDate(LocalDate.now());
        product.setName("laptop");
        product.setPrice(1000L);
    }
}
