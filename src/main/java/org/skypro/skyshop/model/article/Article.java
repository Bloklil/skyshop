package org.skypro.skyshop.model.article;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String nameArticle;
    private final String textArticle;
    private final UUID id;

    public Article(UUID id, String nameArticle, String textArticle) {
        this.nameArticle = nameArticle;
        this.textArticle = textArticle;
        this.id = id;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getNameArticle() + getTextArticle();
    }

    @JsonIgnore
    @Override
    public String getContent() {
        return getTextArticle();
    }

    @Override
    public String getName() {
        return getNameArticle();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(nameArticle, article.nameArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameArticle);
    }

    @Override
    public String toString() {
        return "Article{" +
                "nameArticle='" + nameArticle + '\'' +
                ", textArticle='" + textArticle + '\'' +
                '}';
    }
}