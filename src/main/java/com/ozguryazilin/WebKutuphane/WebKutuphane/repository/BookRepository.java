package com.ozguryazilin.WebKutuphane.WebKutuphane.repository;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    List<Book> findByAuthor(Author author);

    List<Book> findByBookNameIsContainingIgnoreCase(String bookname);

    List<Book> findBySerialNameIsContainingIgnoreCase(String serialname);

    List<Book> findByIsbnNoIsContainingIgnoreCase(String isbnno);


}
