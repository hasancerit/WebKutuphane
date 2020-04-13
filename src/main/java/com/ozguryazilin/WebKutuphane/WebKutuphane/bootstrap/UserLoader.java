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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
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

        User user_both = new User();
        user_both.setUsername("admin");
        user_both.setFirstName("Hasan");
        user_both.setLastName("Cerit");
        user_both.setPassword(bCryptPasswordEncoder.encode("12345"));
        HashSet<Role> roles1 = new HashSet<>();
        roles1.add(role_user);
        roles1.add(role_admin);
        user_both.setRoles(roles1);


        User user_user = new User();
        user_user.setUsername("user");
        user_user.setFirstName("Deniz");
        user_user.setLastName("İnan");
        user_user.setPassword(bCryptPasswordEncoder.encode("12345"));
        HashSet<Role> roles2 = new HashSet<>();
        roles2.add(role_user);
        user_user.setRoles(roles2);

        userRepository.save(user_user);
        userRepository.save(user_both);


        Author author = new Author();
        author.setAuthorFullname("Sabahattin Ali");
        author.setDescription("Sabahattin Ali, Türk yazar ve şair. Edebi kişiliğini toplumcu gerçekçi bir " +
        "düzleme oturtarak yaşamındaki deneyimlerini okuyucusuna yansıttı ve kendisinden sonraki " +
                "cumhuriyet dönemi Türk edebiyatını etkileyen bir figür hâline geldi.");
        authorRepository.save(author);

        Author author2 = new Author();
        author2.setAuthorFullname("Ernest Hemignway");
        author2.setDescription("Ernest Miller Hemingway, Amerikalı romancı, hikâye yazarı ve gazetecidir. " +
                "Basit yazma tekniği ve sade üslubuyla 20. yüzyıl kurgu romancılığını etkilemiştir. Nobel ve Pulitzer Ödülü sahibi " +
                "yazarın çoğu eseri, bugün Amerikan edebiyatının başyapıtlarından kabul edilir.");
        authorRepository.save(author2);

        Author author3 = new Author();
        author3.setAuthorFullname("Reşat Nuri Güntekin");
        author3.setDescription("Reşat Nuri Güntekin, Cumhuriyet dönemi edebiyatında önemli bir yeri olan Çalıkuşu, " +
                "Yeşil Gece ve Anadolu Notları gibi eserlere imza atmış roman, öykü ve oyun yazarıdır. " +
                "Müfettişlik görevi ile Anadolu'da gezdiği için Anadolu insanını yakından tanımıştır.");
        authorRepository.save(author3);

        Author author4 = new Author();
        author4.setAuthorFullname("Tevfik Fikret");
        author4.setDescription("Tevfik Fikret, Türk şair, öğretmen, yayıncı. Osmanlı İmparatorluğu'nun dağılma sürecinde yetişti. " +
                "Servet-i Fünûn topluluğunun lideri olan Tevfik Fikret, devrimci ve idealist fikirleriyle Mustafa Kemal başta " +
                "   olmak üzere dönemin pek çok aydınını etkiledi. Türk edebiyatının Batılılaşmasında büyük pay sahibidir.");
        authorRepository.save(author4);

        Author author5 = new Author();
        author5.setAuthorFullname("Nazım Hikmet");
        author5.setDescription("Nâzım Hikmet Ran ya da kısaca Nâzım Hikmet, Türk şair, oyun yazarı, romancı ve anı yazarı. " +
                "\"Romantik komünist\" ve \"romantik devrimci\" olarak tanımlanır." +
                " Siyasi düşünceleri yüzünden defalarca tutuklanmış ve yetişkin yaşamının büyük bölümünü hapiste ya da" +
                " sürgünde geçirmiştir.");
        authorRepository.save(author5);

        Author author6 = new Author();
        author6.setAuthorFullname("Fyodor Dostoyevski");
        author6.setDescription("Fyodor Mihayloviç Dostoyevski, Rus roman yazarı. Çocukluğu sarhoş bir baba ve hasta bir " +
                "anne arasında geçiren Dostoyevski, annesinin ölümünden sonra Petersburg'daki Mühendis Okulu'na girdi." +
                " Babasının ölüm haberini burada aldı. Okulu başarıyla bitirdikten sonra İstihkâm Müdürlüğü'ne girdi.");
        authorRepository.save(author6);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("İTHAKİ YAYINLARI");
        publisher.setDescription("1997 yılında kurulan ve 2017 itibariyle 1.000' den fazla kitap yayımlamış olan İthaki Yayınları, " +
                "Türkiye’nin en büyük on yayınevinden biri. Fantastik edebiyat ve bilimkurgu türlerinde zirvedeki yerini kimseye " +
                "kaptırmayan İthaki, çağdaş edebiyat, felsefe, tarih, siyaset kuramı, edebiyat eleştirisi, çocuk kitapları," +
                " futbol kitapları, dünya klasikleri ve çizgi roman gibi alanlarda önemli eserler yayınlayarak yerini sağlamlaştırıyor.");
        publisherRepository.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setPublisherName("Profil Yayınevi");
        publisher2.setDescription("2006 yılının Eylül ayında yayın hayatına başlayan Profil Yayıncılık ülkemizin kültür yayıncılığında önemli " +
                "bir boşluğu doldurmaktadır. Yayınladığı kitaplarla kısa sürede geniş bir okuyucu kitlesine ulaşan Profil profesyonel " +
                "bir anlayışla yönetilmektedir.");
        publisherRepository.save(publisher2);

        Publisher publisher3 = new Publisher();
        publisher3.setPublisherName("Elma Yayınevi");
        publisher3.setDescription("Yayınevi, 1998 yılında IAT Eğitim Danışmanlık Ltd. Şti. bünyesinde Academyplus Yayınevi " +
                "markasıyla faaliyete başladı. Kurucu ortaklar; Ahmet Şerif İzgören, Mustafa Baştürk ve Mehmet Özaksu'dur. " +
                "2000 yılında Akademi Artı Yayıncılık Matbaacılık İç ve Dış Ticaret AŞ olarak bağımsız bir firmaya dönüştü. " +
                "2003 yılından bu yana ELMA YAYINEVİ markasıyla faaliyetlerine devam etmektedir.");
        publisherRepository.save(publisher3);


        Book book1 = new Book();
        book1.setBookImage(getBytes("https://i.dr.com.tr/cache/600x600-0/originals/0000000058245-1.jpg"));
        book1.setBookName("Kürk Mantolu Madonna");
        book1.setAuthor(author);
        book1.setPublisher(publisher);
        book1.setBookSubName("Kürk Mantolu Madonna");
        book1.setDescription("Kürk Mantolu Madonna, Sabahattin Ali'nin 1943 yılında yayımladığı bir romanıdır. İlk olarak Hakikat " +
                "gazetesinde 18 Aralık 1940-8 Şubat 1941 tarihinde “Büyük Hikâye” başlığı altında 48 bölüm olarak yayınlanmıştır.");
        book1.setIsbnNo("4321094543254");
        book1.setSerialName("5UW3K1");

        Book bookSame = new Book();
        bookSame.setBookImage(getBytes("https://www.kitapsec.com/image/urun/2019/09/16/11568615931.jpg"));
        bookSame.setBookName("Yeni Dünya");
        bookSame.setAuthor(author);
        bookSame.setPublisher(publisher2);
        bookSame.setBookSubName("Yeni Dünya");
        bookSame.setDescription("Yeni Dünya, Sabahattin Ali'nin 1943 tarihli öykü kitabı. " +
                "Yazarın 1936 ile 1942 yılları arasında gazete ve dergilerde yayımlanmış 13 hikâyesinden oluşmaktadır." +
                " Kitap yazarın hikâye alanında önemli bir yetkinliğe ulaştığının göstergesi olması bakından önemlidir.");
        bookSame.setIsbnNo("4321094543255");
        bookSame.setSerialName("5UW2K1");

        Book book2 = new Book();
        book2.setBookImage(getBytes("https://content.babil.com/urun/35f649378b6f40a68529612738d227cf/Front/Big"));
        book2.setBookName("İhtiyar Balıkçı");
        book2.setAuthor(author2);
        book2.setPublisher(publisher);
        book2.setBookSubName("İhtiyar Balıkçı");
        book2.setDescription("Yaşlı Adam ve Deniz, Ernest Hemingway'in eseri. Eser aynı zamanda birçok ödül de kazanmıştır. " +
                "Hemingway bu hikâyeyi Küba'da yazmıştır ve hikâyenin başkahramanı Kübalı bir balıkçı olan Santiago'dur.");
        book2.setIsbnNo("4321034543255");
        book2.setSerialName("7KW2K1");

        Book book3 = new Book();
        book3.setBookImage(getBytes("https://i.dr.com.tr/cache/600x600-0/originals/0000000291510-1.jpg"));
        book3.setBookName("Silahlara Veda");
        book3.setAuthor(author2);
        book3.setPublisher(publisher3);
        book3.setBookSubName("Silahlara Veda");
        book3.setDescription("Silahlara Veda, Amerikalı yazar Ernest Hemingway'in en önemli romanlarından biridir. Silahlara Veda'da savaşın" +
                " ortasında iki genç insan hem kendi sevgi dolu dünyalarında, hem de savaşın her şeyi yerle bir eden " +
                "acımasız dünyasında yaşarlar.");
        book3.setIsbnNo("4329564312955");
        book3.setSerialName("7IUX1");

        Book book4 = new Book();
        book4.setBookImage(getBytes("https://i.dr.com.tr/cache/500x400-0/originals/0000000052566-1.jpg"));
        book4.setBookName("Çalıkuşu");
        book4.setAuthor(author3);
        book4.setPublisher(publisher3);
        book4.setBookSubName("Çalıkuşu");
        book4.setDescription("Çalıkuşu, Reşat Nuri Güntekin'in ilk defa 1922'de tefrika edilmeye başlanıp 1923'te kitap olarak yayımlanan," +
                " 1937'de büyük değişikliklerle tefrika edilen romanıdır.");
        book4.setIsbnNo("4321034312955");
        book4.setSerialName("7IUB1");

        Book book5 = new Book();
        book5.setBookImage(getBytes("https://i.dr.com.tr/cache/500x400-0/originals/0000000052555-1.jpg"));
        book5.setBookName("Acımak");
        book5.setAuthor(author3);
        book5.setPublisher(publisher2);
        book5.setBookSubName("Acımak");
        book5.setDescription("Acımak, Reşat Nuri Güntekin'in 1928 yılında basılan kısa soluklu romanı." +
                " Eser küçük yaşta gördüğü kötü muamelelerden dolayı acıma duygusu olmayan bir öğretmeninin " +
                "babasının vefatından sonra onun günlüğü okuyarak babası ve hayatı hakkındaki gerçekleri öğrenmesini konu alır.");
        book5.setIsbnNo("4321037112955");
        book5.setSerialName("90UB1");

        Book book6 = new Book();
        book6.setBookImage(getBytes("https://kitapkolik-img.ticimaxcdn.com/Uploads/UrunResimleri/buyuk/sermin---tevfik-fikret-ea4d.jpg"));
        book6.setBookName("Şermin");
        book6.setAuthor(author4);
        book6.setPublisher(publisher3);
        book6.setBookSubName("Şermin");
        book6.setDescription("Şermin, Tevfik Fikret'in 1914 yılında çocuklar için yazdığı şiirlerden oluşan ve aynı yıl yayımlanmış olan kitabı." +
                " Ünlü şairin son eseri olan kitap, Türk edebiyatının “ilk eğitsel çocuk kitabı” ve çocuk şiiri alanında Türkçedeki ilk " +
                "örneklerden biri kabul edilir. Kitapta otuz bir adet şiir yer alır.");
        book5.setIsbnNo("4001037112955");
        book6.setSerialName("9AXB1");

        Book book7 = new Book();
        book7.setBookImage(getBytes("https://cdn1.dokuzsoft.com/u/ilknokta/img/c/h/a/halukun-defterif750c4102d08bd02633126da73973d02.jpg"));
        book7.setBookName("Haluk'un Defteri");
        book7.setAuthor(author4);
        book7.setPublisher(publisher);
        book7.setBookSubName("Haluk'un Defteri");
        book7.setDescription("1911'de yayımlanan Haluk'un Defteri; Edebiyât-ı Cedîde'nin öncü şari Tevfik Fikret'in (1867-1915)" +
                " ilk kitabı Rübab-ı Şikeste'den sonra yazdığı eserdir. “Haluk'un Defteri, Hayata Karşı Beşer, Hitabeler” başlıklı" +
                " üç bölümden oluşan eserde, 20 şiir bulunmaktadır.");
        book7.setIsbnNo("4001567112955");
        book7.setSerialName("9AXC2");

        Book book8 = new Book();
        book8.setBookImage(getBytes("https://i.dr.com.tr/cache/500x400-0/originals/0000000408958-1.jpg"));
        book8.setBookName("Piraye'ye Mektuplar");
        book8.setAuthor(author5);
        book8.setPublisher(publisher2);
        book8.setBookSubName("Piraye'ye Mektuplar");
        book8.setDescription("Nâzım'ın, 1933'ten 1950'ye kadar, on yedi yıl boyunca, çeşitli cezaevlerinden kendisine yazdığı " +
                "mektupları, Piraye bir tahta bavulda saklardı. Ceviz ağacından yapılmış, 41x26x14 cm boyutlarında küçük " +
                " tahta bavul. Küçük olduğu için, belki \"çanta\" demek daha doğru. Bu ceviz çantayı ona Nâzım sanırım Çankırı " +
                "Cezaevi'ndeyken yapmıştı.");
        book8.setIsbnNo("4009067112955");
        book8.setSerialName("9AXC2");

        Book book9 = new Book();
        book9.setBookImage(getBytes("https://i.dr.com.tr/cache/600x600-0/originals/0000000119710-1.jpg"));
        book9.setBookName("Yaşamak Güzel Şey Be Kardeşim");
        book9.setAuthor(author5);
        book9.setPublisher(publisher3);
        book9.setBookSubName("Yaşamak Güzel Şey Be Kardeşim");
        book9.setDescription("Yaşamak Güzel Şey Be Kardeşim, Nâzım Hikmet'in bir romanıdır. 1962 yılında yazılan kitap," +
                " Türkiye'de 1966'da basıldı. Romanın kahramanı Ahmet'in, İstanbul, Anadolu ve Sovyetler Birliği'nde geçen " +
                "hikâyesini anlatmaktadır.");
        book9.setIsbnNo("4002367112955");
        book9.setSerialName("9AXX2");

        Book book10 = new Book();
        book10.setBookImage(getBytes("https://i.dr.com.tr/cache/600x600-0/originals/0000000060365-1.jpg"));
        book10.setBookName("Karamazov Kardeşler");
        book10.setAuthor(author6);
        book10.setPublisher(publisher2);
        book10.setBookSubName("Karamazov Kardeşler");
        book10.setDescription("Karamazov Kardeşler, Rus yazar Dostoyevski'nin romanıdır. Dostoyevski'nin hayatının " +
                "zirve romanı olarak bilinir. Romanın büyük bir bölümü Staraya Russa'da yazılmıştır. Dostoyevski, oldukça ağır " +
                "bir dili olan roman için iki yıla yakın zaman harcamış ve 1880 yılının Kasım ayında bitirmişti");
        book10.setIsbnNo("4001513112955");
        book10.setSerialName("11XC2");

        Book book11 = new Book();
        book11.setBookImage(getBytes("https://i.dr.com.tr/cache/500x400-0/originals/0001755819001-1.jpg"));
        book11.setBookName("Yeraltından Notlar");
        book11.setAuthor(author6);
        book11.setPublisher(publisher);
        book11.setBookSubName("Yeraltından Notlar");
        book11.setDescription("Yeraltından Notlar, Dostoyevski'nin, Camus dahil olmak üzere birçok Batılı düşünürü varoluşçu " +
                "anlamda etkilemiş bir klasik olarak kabul edilen kısa romanıdır. 1864 yılında Petersburg'da basılmıştır.");
        book11.setIsbnNo("4001519815555");
        book11.setSerialName("78XC2");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(bookSame);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);
        bookRepository.save(book9);
        bookRepository.save(book10);
        bookRepository.save(book11);
    }

    private byte[] getBytes(String url){
        BufferedImage image = null;
        ByteArrayOutputStream output = null;

        try {
            image = ImageIO.read(new URL(url));
            output = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", output );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toByteArray();
    }
}
