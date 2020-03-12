package com.ozguryazilin.WebKutuphane.WebKutuphane.service.imp;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.RoleRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.UserRepository;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void handleSignupUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRole("USER");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void handleSignupAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role roleAdmin = roleRepository.findByRole("ADMIN");
        Role roleUser = roleRepository.findByRole("USER");
        roles.add(roleAdmin);
        roles.add(roleUser);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
