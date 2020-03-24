package com.ozguryazilin.WebKutuphane.WebKutuphane.util;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import org.apache.tomcat.util.codec.binary.Base64;

public class ImageHelper {
    public static Book convertToBase64(Book book){
        if(book.getBookImage() != null){
            /*Util base64 kullan*/
            String image = Base64.encodeBase64URLSafeString(book.getBookImage());
            image = image.replace('_','/');
            image = image.replace('-','+');
            book.setBookImage64(image);
        }
        return book;
    }
}
