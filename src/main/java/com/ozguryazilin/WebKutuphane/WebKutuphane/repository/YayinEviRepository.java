package com.ozguryazilin.WebKutuphane.WebKutuphane.repository;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.YayinEvi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YayinEviRepository extends JpaRepository<YayinEvi,String> {
}
