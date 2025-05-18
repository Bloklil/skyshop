package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();

    String getContent();

    String getName();

    UUID getId();

    default String getStringRepresentation() {
        return getName() + " - " + getContent();
    }
}