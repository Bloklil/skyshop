package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.backet.BasketItem;
import org.skypro.skyshop.model.backet.ProductBasket;
import org.skypro.skyshop.model.backet.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        storageService.getProductById(id)
                .orElseThrow(() -> new NoSuchProductException("Продукт с id: " + id + " не найден."));
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketContents = productBasket.getAllProducts();
        List<BasketItem> items = basketContents.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(id)
                            .orElseThrow(() ->new NoSuchProductException("Продукт с id: " + id + " не найден."));
                    return new BasketItem(product, quantity);
                })
                .toList();
        return new UserBasket(items);
    }


}
