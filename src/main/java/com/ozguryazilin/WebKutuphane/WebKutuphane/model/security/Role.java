package com.ozguryazilin.WebKutuphane.WebKutuphane.model.security;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;
    private String role;

    public Role() {
    }

    public Role(String role){
        this.role = role;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}