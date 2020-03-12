package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import javax.persistence.*;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String bookNme;
    private String bookSubName;
    private String description;
    private String serialName;
    private String isbnNo;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {})
    @JoinColumn
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {})
    @JoinColumn
    private Publisher publisher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookNme() {
        return bookNme;
    }

    public void setBookNme(String bookNme) {
        this.bookNme = bookNme;
    }

    public String getBookSubName() {
        return bookSubName;
    }

    public void setBookSubName(String bookSubName) {
        this.bookSubName = bookSubName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    public String getIsbnNo() {
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo) {
        this.isbnNo = isbnNo;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
