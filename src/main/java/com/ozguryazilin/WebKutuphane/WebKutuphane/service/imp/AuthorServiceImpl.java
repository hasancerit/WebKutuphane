package com.ozguryazilin.WebKutuphane.WebKutuphane.service.imp;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.AuthorRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.BookRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.AuthorService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.util.ImageHelper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> searchBook(String action, String search) {
        List<Author> authors = new ArrayList<>();
        switch (action){
            case "1":
                authors = authorRepository.findByAuthorFullnameIsContainingIgnoreCase(search);
                break;
            case "2":
                List<Book> books = bookRepository.findByBookNameIsContainingIgnoreCase(search);
                for(Book book : books)
                    authors.add(book.getAuthor());
                break;
        }
        return authors;
    }

    @Override
    public Author getAuthor(String id){
        Author author = authorRepository.getOne(id);
        for(Book book : author.getBooks()){
            book = ImageHelper.convertToBase64(book);
        }
        return author;
    }

    @Override
    public void deleteAuthor(String id) {
        Author author = authorRepository.getOne(id);
        for(Book b : author.getBooks()){
            b.setAuthor(null);
            bookRepository.save(b);
        }
        authorRepository.delete(author);
    }

    @Override
    public void addAuthor(Author author) {
        /*
        * Author adı boş ise hata fırlatılacak
         */
        authorRepository.save(author);
    }
}
