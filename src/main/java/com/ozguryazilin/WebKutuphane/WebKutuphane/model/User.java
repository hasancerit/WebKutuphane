package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String isim;
    private String soyisim;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
}
