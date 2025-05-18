package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class DiscountedProduct extends Product implements Searchable {
    private final int basePrice;
    private final int discountPersent;

    public int getPrice() {
        return basePrice * (100 - discountPersent) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getPrice() + " (скидка: " + discountPersent + " %)";
    }

    @Override
    public String getSearchTerm() {
        return getNameProduct();
    }

    @Override
    public String getName() {
        return getNameProduct();
    }

    public DiscountedProduct(UUID id, String nameProduct, int basePrice, int discountPersent) {
        super(id, nameProduct);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше 0.");
        }
        if (discountPersent < 0 || discountPersent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0% до 100%");
        }
        this.basePrice = basePrice;
        this.discountPersent = discountPersent;
    }
}