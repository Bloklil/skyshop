package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        loadTestData();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    private void loadTestData() {

        Product banan = new SimpleProduct(UUID.randomUUID(), "банан", 123);
        Product cocos = new SimpleProduct(UUID.randomUUID(), "кокос", 678);
        Product chleb = new SimpleProduct(UUID.randomUUID(), "хлеб", 23);
        Product vinograd = new SimpleProduct(UUID.randomUUID(), "виноград", 345);
        Product gribi = new SimpleProduct(UUID.randomUUID(), "грибы", 123);
        Product grusha = new SimpleProduct(UUID.randomUUID(), "груша", 64);
        Product ris = new SimpleProduct(UUID.randomUUID(), "рис", 99);
        Product grechka = new SimpleProduct(UUID.randomUUID(), "гречка", 123);
        Product sliva = new SimpleProduct(UUID.randomUUID(), "слива", 12);
        Product water = new DiscountedProduct(UUID.randomUUID(), "вода", 100, 10);
        Product limonad = new DiscountedProduct(UUID.randomUUID(), "лимонад", 409, 15);
        Product konyak = new FixPriceProduct(UUID.randomUUID(), "коньяк");
        Product popcorn = new FixPriceProduct(UUID.randomUUID(), "попкорн");

        products.put(banan.getId(), banan);
        products.put(cocos.getId(), cocos);
        products.put(chleb.getId(), chleb);
        products.put(vinograd.getId(), vinograd);
        products.put(gribi.getId(), gribi);
        products.put(grusha.getId(), grusha);
        products.put(ris.getId(), ris);
        products.put(grechka.getId(), grechka);
        products.put(sliva.getId(), sliva);
        products.put(water.getId(), water);
        products.put(limonad.getId(), limonad);
        products.put(konyak.getId(), konyak);
        products.put(popcorn.getId(), popcorn);

        Article article1 = new Article(UUID.randomUUID(), "про бананы", "бананами нельзя кормить обезъян");
        Article article2 = new Article(UUID.randomUUID(), "хлеб-вреден", "хлеб вреден после 40, но это не точно");
        Article article3 = new Article(UUID.randomUUID(), "полезные статьи про молоко", "как же хорошо когда есть молоко");
        Article article4 = new Article(UUID.randomUUID(), "чай", "он тонизирует на весь день");
        Article article5 = new Article(UUID.randomUUID(), "чай про", "хороший состав");
        Article article6 = new Article(UUID.randomUUID(), "чай каркаде", "хороший состав");
        Article article7 = new Article(UUID.randomUUID(), "чай фи", "хороший состав");
        Article article8 = new Article(UUID.randomUUID(), "чай каскарпоне", "хороший состав");
        Article article9 = new Article(UUID.randomUUID(), "кофе", "кофе вреден из-за кофеина в больших количествах");
        Article article10 = new Article(UUID.randomUUID(), "цикорий", "прекрасная замена кофе");
        Article article11 = new Article(UUID.randomUUID(), "цикорий мен", "прекрасная замена кофе");

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
        articles.put(article3.getId(), article3);
        articles.put(article4.getId(), article4);
        articles.put(article5.getId(), article5);
        articles.put(article6.getId(), article6);
        articles.put(article7.getId(), article7);
        articles.put(article8.getId(), article8);
        articles.put(article9.getId(), article9);
        articles.put(article10.getId(), article10);
        articles.put(article11.getId(), article11);
    }
}