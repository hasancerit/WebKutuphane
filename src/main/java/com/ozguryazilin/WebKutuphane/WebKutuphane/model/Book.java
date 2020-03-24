package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String bookName;
    private String bookSubName;

    @Column(length = 400)
    private String description;
    private String serialName;

    @Column(unique = true)
    private String isbnNo;

    @Lob
    private byte[] bookImage;

    @Transient
    private String bookImage64;

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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getBookImage64() {
        return bookImage64;
    }

    public void setBookImage64(String bookImage64) {
        this.bookImage64 = bookImage64;
    }

    public byte[] getBookImage() {
        return bookImage;
    }

    public void setBookImage(byte[] bookImage) {
        this.bookImage = bookImage;
    }
}
