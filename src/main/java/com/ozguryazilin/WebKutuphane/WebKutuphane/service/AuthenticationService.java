package com.ozguryazilin.WebKutuphane.WebKutuphane.service;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;

public interface AuthenticationService {
    void handleSignupUser(User user);

    void handleSignupAdmin(User user);
}
