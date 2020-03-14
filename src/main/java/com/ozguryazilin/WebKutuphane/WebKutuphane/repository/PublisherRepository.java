package com.ozguryazilin.WebKutuphane.WebKutuphane.repository;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,String> {
    Publisher findByPublisherName(String publisherName);
}
