package com.ozguryazilin.WebKutuphane.WebKutuphane.bootstrap;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.RoleRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Role role_admin = new Role();
        role_admin.setRole("ADMIN");

        Role role_user = new Role();
        role_user.setRole("USER");

        role_user = roleRepository.save(role_user);
        role_admin = roleRepository.save(role_admin);


        User user_admin = new User();
        user_admin.setUsername("hasan");
        user_admin.setSifre(bCryptPasswordEncoder.encode("123"));
        HashSet<Role> roles1 = new HashSet<>();
        roles1.add(role_admin);
        user_admin.setRoles(roles1);

        User user_user = new User();
        user_user.setUsername("burak");
        user_user.setSifre(bCryptPasswordEncoder.encode("123"));
        HashSet<Role> roles2 = new HashSet<>();
        roles2.add(role_user);
        user_user.setRoles(roles2);

        User user_both = new User();
        user_both.setUsername("onur");
        user_both.setSifre(bCryptPasswordEncoder.encode("123"));
        HashSet<Role> roles3 = new HashSet<>();
        roles3.add(role_user);
        roles3.add(role_admin);
        user_both.setRoles(roles3);

        userRepository.save(user_admin);
        userRepository.save(user_user);
        userRepository.save(user_both);

    }
}
