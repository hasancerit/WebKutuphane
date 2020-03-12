package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String isim;
    private String soyisim;
    private String sifre;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @ManyToMany
    private Set<Role> roles;
}
