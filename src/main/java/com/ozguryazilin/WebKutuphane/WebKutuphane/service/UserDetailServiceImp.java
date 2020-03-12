package com.ozguryazilin.WebKutuphane.WebKutuphane.service;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImp  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println(user.getPassword());
        if (user == null) {
            //ExceptionHandler ile anlamlı bir sayfaya yönlenecek
            throw new UsernameNotFoundException(username);
        }


        Set<GrantedAuthority> roles = new HashSet<>();
        System.out.println(user.getRoles().size());
        for(Role r : user.getRoles())
            roles.add(new SimpleGrantedAuthority(r.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }
}
