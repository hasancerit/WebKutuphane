package com.ozguryazilin.WebKutuphane.WebKutuphane.service.imp;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.BookRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.PublisherRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.PublisherService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.util.ImageHelper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisher(String id){
        Publisher publisher = publisherRepository.getOne(id);
        for(Book book : publisher.getBooks()){
            book = ImageHelper.convertToBase64(book);
        }
        return publisher;
    }

    @Override
    public List<Publisher> searchBook(String action, String search) {
        List<Publisher> publishers = new ArrayList<>();
        switch (action){
            case "1":
                publishers = publisherRepository.findByPublisherNameIsContainingIgnoreCase(search);
                break;
            case "2":
                List<Book> books = bookRepository.findByBookNameIsContainingIgnoreCase(search);
                for(Book book : books)
                    publishers.add(book.getPublisher());
                break;
        }
        return publishers;
    }


    @Override
    public void deletePublisher(String id) {
        Publisher publisher = publisherRepository.getOne(id);
        for(Book b : publisher.getBooks()){
            b.setPublisher(null);
            bookRepository.save(b);
        }
        publisherRepository.delete(publisher);
    }

    @Override
    public void addPublisher(Publisher publisher) {
        /*
        *publisher adı boş ise hata fırlatılacak
         */
        publisherRepository.save(publisher);
    }

}
