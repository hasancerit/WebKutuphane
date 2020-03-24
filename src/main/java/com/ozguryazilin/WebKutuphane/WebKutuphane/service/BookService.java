package com.ozguryazilin.WebKutuphane.WebKutuphane.service;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> allBooks();

    List<Book> searchBook(String action, String search);

    Book getBook(String id);

    void deleteBook(String id);

    void updateImage(String id,byte[] image);
}
