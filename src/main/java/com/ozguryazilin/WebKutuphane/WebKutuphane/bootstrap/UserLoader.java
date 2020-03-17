package com.ozguryazilin.WebKutuphane.WebKutuphane.bootstrap;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.security.Role;
import com.ozguryazilin.WebKutuphane.WebKutuphane.repository.*;
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
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

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
        user_admin.setFirstName("Hasan");
        user_admin.setLastName("Cerit");
        user_admin.setPassword(bCryptPasswordEncoder.encode("123"));
        HashSet<Role> roles1 = new HashSet<>();
        roles1.add(role_admin);
        user_admin.setRoles(roles1);

        User user_user = new User();
        user_user.setUsername("burak");
        user_user.setPassword(bCryptPasswordEncoder.encode("123"));
        HashSet<Role> roles2 = new HashSet<>();
        roles2.add(role_user);
        user_user.setRoles(roles2);

        User user_both = new User();
        user_both.setUsername("onur");
        user_both.setPassword(bCryptPasswordEncoder.encode("123"));
        HashSet<Role> roles3 = new HashSet<>();
        roles3.add(role_user);
        roles3.add(role_admin);
        user_both.setRoles(roles3);

        userRepository.save(user_admin);
        userRepository.save(user_user);
        userRepository.save(user_both);


        Author author = new Author();
        author.setAuthorFullname("Hasan Cerit");
        author.setDescription("22 Yaşında yazar.22 Yaşında yazar.22 Yaşında yazar.22 Yaşında yazar.");
        authorRepository.save(author);

        Author author2 = new Author();
        author2.setAuthorFullname("deniz inan");
        author2.setDescription("İngiliz Dili ve Edebiyatı");
        authorRepository.save(author2);

        Author author3 = new Author();
        author3.setAuthorFullname("onur ekin");
        author3.setDescription("Kanadalı ünlü yazar onur ekin gün");
        authorRepository.save(author3);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Özgür Yayın Evi");
        publisher.setDescription("Özgür Yazılım Yayın Evi ");
        publisherRepository.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setPublisherName("Özgür Yayın Evi");
        publisher2.setDescription("Özgür Yazılım Yayın Evi ");
        publisherRepository.save(publisher2);

        Publisher publisher3 = new Publisher();
        publisher3.setPublisherName("Özgür Yayın Evi");
        publisher3.setDescription("Özgür Yazılım Yayın Evi ");
        publisherRepository.save(publisher3);


        Book book1 = new Book();
        book1.setBookName("Book1");
        book1.setAuthor(author);
        book1.setPublisher(publisher);
        book1.setBookSubName("Book1 Sub Name");
        book1.setDescription("Book1 Description");
        book1.setIsbnNo("123");
        book1.setSerialName("s123");

        Book bookSame = new Book();
        bookSame.setBookName("Book1");
        bookSame.setAuthor(author2);
        bookSame.setPublisher(publisher2);
        bookSame.setBookSubName("Book5 Sub Name");
        bookSame.setDescription("Book5 Description");
        bookSame.setIsbnNo("1235");
        bookSame.setSerialName("s1235");

        Book book2 = new Book();
        book2.setBookName("Book2");
        book2.setAuthor(author);
        book2.setPublisher(publisher);
        book2.setBookSubName("Book2 Sub Name");
        book2.setDescription("Book2 Description");
        book2.setIsbnNo("123");
        book2.setSerialName("s124");

        Book book3 = new Book();
        book3.setBookName("Book3");
        book3.setAuthor(author);
        book3.setPublisher(publisher);
        book3.setBookSubName("Book3 Sub Name");
        book3.setDescription("With supporting  content.\n" +
                "                        With supporting text below as a natural lead-in to additional content.\n" +
                "                        With supporting text below as a natural lead-in to additional content.");
        book3.setIsbnNo("124");
        book3.setSerialName("s125");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(bookSame);

    }
}
