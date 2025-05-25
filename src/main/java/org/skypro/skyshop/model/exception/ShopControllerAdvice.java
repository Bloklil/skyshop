package org.skypro.skyshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handProductException(NoSuchProductException e) {
        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND-404",
                "\uD83D\uDE22\uD83D\uDE22Товар не найден: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
