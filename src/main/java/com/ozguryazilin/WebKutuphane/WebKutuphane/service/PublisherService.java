package com.ozguryazilin.WebKutuphane.WebKutuphane.service;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAll();

    List<Publisher> searchBook(String action, String search);

    Publisher getPublisher(String id);

    void deletePublisher(String id);

    void addPublisher(Publisher publisher);
}
