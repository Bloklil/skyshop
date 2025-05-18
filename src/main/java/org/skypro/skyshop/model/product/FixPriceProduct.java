package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class FixPriceProduct extends Product implements Searchable {
    private static final int fixPrice = 199;

    public FixPriceProduct(UUID id, String nameProduct) {
        super(id, nameProduct);
    }

    @Override
    public int getPrice() {
        return fixPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", фиксированная цена: " + getPrice() + " рублей.";
    }

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getName() {
        return getNameProduct();
    }
}