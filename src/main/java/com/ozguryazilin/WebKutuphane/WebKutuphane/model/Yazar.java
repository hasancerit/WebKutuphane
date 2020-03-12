package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class Yazar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String yazarAdi;
    private String aciklama;

    @OneToMany(mappedBy = "yazar",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Kitap> kitaplar;
}
