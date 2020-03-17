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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
        List<Publisher> publishers = publisherRepository.findByPublisherName(book.getPublisher().getPublisherName());
        Publisher publisher;
        if(publishers.size() == 0){
            publisher = book.getPublisher();
            publisher.setDescription("");

            publisher = publisherRepository.save(publisher);
        }else {
            System.out.println(publishers.get(0).getPublisherName());
            publisher = publishers.get(0);
        }


        List<Author> authors = authorRepository.findByAuthorFullname(book.getAuthor().getAuthorFullname());
        Author author;
        if(authors.size() == 0){
            author = book.getAuthor();
            author.setDescription("");

            author = authorRepository.save(author);
        }else{
           author = authors.get(0);
        }


        book.setPublisher(publisher);
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Override
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBook(String action, String search) {
        List<Book> books = new ArrayList<>();
        switch (action){
            case "1":
                books = bookRepository.findByBookName(search);
                break;
            case "2":
                List<Author> authors = authorRepository.findByAuthorFullname(search);
                for(Author author : authors){
                    Set<Book> authorsBooks = author.getBooks();
                    for(Book book : authorsBooks){
                        books.add(book);
                    }
                }
                break;
            case "3":
                books = bookRepository.findBySerialName(search);
                break;
            case "4":
                books = bookRepository.findByIsbnNo(search);
                break;
        }
        return books;
    }

    @Override
    public Book getBook(String id){
        return bookRepository.getOne(id);
    }

    @Override
    public void deleteBook(String id){
        bookRepository.delete(bookRepository.getOne(id));
    }
}
