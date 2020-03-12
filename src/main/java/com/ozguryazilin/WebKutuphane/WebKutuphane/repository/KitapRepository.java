package com.ozguryazilin.WebKutuphane.WebKutuphane.repository;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapRepository extends JpaRepository<Kitap,String> {

}
