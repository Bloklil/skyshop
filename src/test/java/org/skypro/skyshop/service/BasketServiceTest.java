package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.backet.ProductBasket;
import org.skypro.skyshop.model.backet.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private final UUID productId = UUID.randomUUID();
    private final Product milk = new SimpleProduct(productId, "молоко", 23);

    @Test
    void addToBasket_whenProductNotFound_trowsException() {
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(productId));
    }

    @Test
    void addToBasket_whenProductExist_addToBasket() {
        when(storageService.getProductById(productId)).thenReturn(Optional.of(milk));

        basketService.addProductToBasket(productId);

        verify(productBasket).addProduct(milk.getId());
    }

    @Test
    void getUserBasket_whenBasketHasProduct_returnCorrect() {
        Product banan = new SimpleProduct(UUID.randomUUID(), "банан", 34);
        Product chleb = new SimpleProduct(UUID.randomUUID(), "хлеб", 12);
        Product vinograd = new SimpleProduct(UUID.randomUUID(), "виноград", 432);

        Map<UUID, Integer> basketMap = Map.of(
                banan.getId(), 2,
                chleb.getId(), 1,
                vinograd.getId(), 1
        );
        when(productBasket.getAllProducts()).thenReturn(basketMap);
        when(storageService.getProductById(banan.getId())).thenReturn(Optional.of(banan));
        when(storageService.getProductById(chleb.getId())).thenReturn(Optional.of(chleb));
        when(storageService.getProductById(vinograd.getId())).thenReturn(Optional.of(vinograd));

        UserBasket result = basketService.getUserBasket();

        assertEquals(12 + 2 * 34 + 432, result.getTotal());
    }

    @Test
    void getUserBasket_whenBasketEmpty_returnEmptyBasket() {
        when(productBasket.getAllProducts()).thenReturn(Map.of());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getItems().isEmpty());
        assertEquals(0.0, result.getTotal());
    }

}
