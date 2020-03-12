package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String kitapAdi;
    private String kitapAltAdi;
    private String aciklama;
    private String seriAdi;
    private String ısbnNo;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {})
    @JoinColumn
    private Yazar yazar;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {})
    @JoinColumn
    private YayinEvi yayinEvi;


}
