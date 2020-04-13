package com.ozguryazilin.WebKutuphane.WebKutuphane.service.imp;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.AuthorRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.BookRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.PublisherRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.util.ImageHelper;
import org.apache.tomcat.util.codec.binary.Base64;
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
        //Girilen bir publisher var ise
        if(!(book.getPublisher().getPublisherName().equalsIgnoreCase(""))){
            List<Publisher> publishers = publisherRepository.findByPublisherNameIsContainingIgnoreCase(book.getPublisher().getPublisherName());
            Publisher publisher;
            //Fakat veri tabanında yok ise
            if(publishers.size() == 0){
                //Publisher'ı name ile birlikte kaydet.
                publisher = book.getPublisher();
                publisher.setDescription("");

                publisher = publisherRepository.save(publisher);
            }else { //veri tabanında da varsa, ilk publisherı al
                publisher = publishers.get(0);
            }
            book.setPublisher(publisher);
        }else{ //Kullanici publisher girmediyse
            book.setPublisher(null);
        }


        if(!book.getAuthor().getAuthorFullname().equalsIgnoreCase("")){
            List<Author> authors = authorRepository.findByAuthorFullnameIsContainingIgnoreCase(book.getAuthor().getAuthorFullname());
            Author author;
            if(authors.size() == 0){
                author = book.getAuthor();
                author.setDescription("");
                author = authorRepository.save(author);
            }else{
                author = authors.get(0);
            }

            book.setAuthor(author);
        }else{
            book.setAuthor(null);
        }

        bookRepository.save(book);
    }

    @Override
    public List<Book> allBooks() {
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            book = ImageHelper.convertToBase64(book);

        }
        return books;
    }

    @Override
    public List<Book> searchBook(String action, String search) {
        List<Book> books = new ArrayList<>();
        switch (action){
            case "1":
                books = bookRepository.findByBookNameIsContainingIgnoreCase(search);
                break;
            case "2":
                List<Author> authors = authorRepository.findByAuthorFullnameIsContainingIgnoreCase(search);
                for(Author author : authors){
                    Set<Book> authorsBooks = author.getBooks();
                    for(Book book : authorsBooks){
                        books.add(book);
                    }
                }
                break;
            case "3":
                books = bookRepository.findBySerialNameIsContainingIgnoreCase(search);
                break;
            case "4":
                books = bookRepository.findByIsbnNoIsContainingIgnoreCase(search);
                break;
        }
        for(Book book : books){
            book = ImageHelper.convertToBase64(book);
        }
        return books;
    }

    @Override
    public Book getBook(String id){
        Book book = bookRepository.getOne(id);
        book = ImageHelper.convertToBase64(book);
        return book;
    }

    @Override
    public void deleteBook(String id){
        bookRepository.delete(bookRepository.getOne(id));
    }

    @Override
    public void updateImage(String id,byte[] image){
        Book book = bookRepository.getOne(id);
        book.setBookImage(image);
        bookRepository.save(book);
    }
}
