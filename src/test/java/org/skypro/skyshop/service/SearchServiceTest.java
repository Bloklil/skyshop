package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private Product createProduct(String name) {
        return new SimpleProduct(UUID.randomUUID(), name, 111);
    }

    private Article createArticle(String name, String text) {
        return new Article(UUID.randomUUID(), name, text);
    }

    @Test
    void search_whenStorageIsEmpty_returnEmptyList() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        List<SearchResult> result = searchService.search("банан");

        assertTrue(result.isEmpty());
    }

    @Test
    void search_whenNoMatch_returnEmptyList() {
        List<Searchable> searchables = List.of(
                createProduct("банан"),
                createProduct("молоко"),
                createProduct("чай"),
                createArticle("чай", "мой любимый")
        );
        when(storageService.getAllSearchables()).thenReturn(searchables);

        List<SearchResult> result = searchService.search("коньяк");

        assertTrue(result.isEmpty());
    }

    @Test
    void search_whenMatchExists_returnsMatchingObject() {
        Product product = createProduct("молоко");
        List<Searchable> searchables = List.of(
                createProduct("чай"),
                createProduct("коньяк"),
                product,
                createArticle("кофе", "не для меня")
        );
        when(storageService.getAllSearchables()).thenReturn(searchables);

        List<SearchResult> result = searchService.search("молоко");

        assertEquals(1, result.size());
    }

    @Test
    void search_isCaseInsensitive() {
        Product product = createProduct("МоЛоКо");
        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        List<SearchResult> result = searchService.search("молоко");

        assertEquals(1, result.size());
    }
}