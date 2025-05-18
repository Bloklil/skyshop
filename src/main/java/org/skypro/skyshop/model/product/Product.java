package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String nameProduct;
    private final UUID id;

    public Product(UUID id, String nameProduct) {
        if (nameProduct == null || nameProduct.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым.");
        }
        this.nameProduct = nameProduct;
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String toString() {
        return nameProduct;
    }

    @Override
    public String getContent() {
        return toString();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }
}