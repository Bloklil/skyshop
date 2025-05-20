package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class SimpleProduct extends Product implements Searchable {
    private final int priceProduct;

    public SimpleProduct(UUID id, String nameProduct, int priceProduct) {
        super(id, nameProduct);
        if (priceProduct <= 0) {
            throw new IllegalArgumentException("Цена товара должна быть больше 0.");
        }
        this.priceProduct = priceProduct;
    }

    @Override
    public int getPrice() {
        return priceProduct;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getPrice() + " рублей.";
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName();
    }

    @JsonIgnore
    @Override
    public String getName() {
        return getNameProduct();
    }
}