package com.ozguryazilin.WebKutuphane.WebKutuphane.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class YayinEvi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String yayinEviAdi;
    private String aciklama;

    @OneToMany(mappedBy = "yayinEvi",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Kitap> kitaplar;
}
