package com.ozguryazilin.WebKutuphane.WebKutuphane.service;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> allAuthors();

    List<Author> searchBook(String action, String search);

    Author getAuthor(String id);

    void deleteAuthor(String id);

    void addAuthor(Author author);
}
