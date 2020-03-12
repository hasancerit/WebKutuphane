package com.ozguryazilin.WebKutuphane.WebKutuphane.model;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private String password;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
