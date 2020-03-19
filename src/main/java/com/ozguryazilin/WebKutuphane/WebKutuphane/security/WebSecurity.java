package com.ozguryazilin.WebKutuphane.WebKutuphane.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/resources/**", "/static/**","/css/**", "/img/**","/webjars/**").permitAll()
                .antMatchers("/signup","/admin/signup","/successlogout").permitAll()
                .antMatchers("/test").hasAnyAuthority("ADMIN")
                .antMatchers("/book/delete/**","/author/delete/**","publisher/delete/**").hasAnyAuthority("ADMIN")
                .antMatchers("/book/update/**","/author/update/**","publisher/update/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .successHandler(loginSuccessHandler)
                .permitAll()
                .and()

                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/successlogout")
                .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
