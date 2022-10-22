package org.marusosa.annotations.example.models;

import org.marusosa.annotations.example.JsonAttribute;

import java.time.LocalDate;

public class Product {

    @JsonAttribute(name = "description")
    private String name;
    @JsonAttribute
    private Long price;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
