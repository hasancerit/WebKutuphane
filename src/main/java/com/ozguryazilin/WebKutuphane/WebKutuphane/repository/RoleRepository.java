package com.ozguryazilin.WebKutuphane.WebKutuphane.repository;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    public Role findByRole(String role);
}
