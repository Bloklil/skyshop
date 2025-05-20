package org.skypro.skyshop.model.backet;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private final Product product;
    private final double quantity;

    public BasketItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }
}
