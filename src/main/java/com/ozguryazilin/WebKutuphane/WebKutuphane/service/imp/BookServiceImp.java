package com.ozguryazilin.WebKutuphane.WebKutuphane.service.imp;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.AuthorRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.BookRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.PublisherRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImp implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public void addBook(Book book) {
        Publisher publisher = publisherRepository.findByPublisherName(book.getPublisher().getPublisherName());
        Author author = authorRepository.findByAuthorNameAndAuthorLastName(book.getAuthor().getAuthorName(),book.getAuthor().getAuthorLastName());
        if(publisher == null){
            publisher = book.getPublisher();
            publisher.setDescription("");

            publisher = publisherRepository.save(publisher);
        }

        if(author == null){
            author = book.getAuthor();
            author.setDescription("");

            author = authorRepository.save(author);
        }

        book.setPublisher(publisher);
        book.setAuthor(author);

        bookRepository.save(book);
    }
}
