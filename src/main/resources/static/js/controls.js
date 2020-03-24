/* Sign Up*/
var validateSignup = function (){
    var bool = true;
    var form = document.getElementById("signupform");
    var firstname = form.elements["firstName"].value;
    var lastname = form.elements["lastname"].value;
    var password = form.elements["password"].value;
    var repassword = form.elements["repassword"].value;
    var username = form.elements["username"].value;

    var messages = document.getElementById("messages");
    messages.innerHTML = "";

    if(firstname == ""){
        messages.innerHTML = messages.innerHTML+"<br/>İsim Boş Bırakılamaz.";
        bool = false;
    }
    if(lastname == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Soyisim Boş Bırakılamaz.";
        bool = false;
    }
    if(password == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Password Boş Bırakılamaz.";
        bool = false;
    }else{
        if(password.length < 5){
            messages.innerHTML = messages.innerHTML+"<br/>Password En Az 5 Haneli olmalıdır.";
            bool = false;
        }
    }
    if(username == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Username Boş Bırakılamaz.";
        bool = false;
    }else{
        if(username.length < 5){
            messages.innerHTML = messages.innerHTML+"<br/>Username En Az 5 Haneli olmalıdır.";
            bool = false;
        }
    }


    if(password != repassword){
        messages.innerHTML = messages.innerHTML + "<br/>Şifre Doğrulama Hatalı";
        bool = false;
    }

    console.log(bool);
    return bool;
}


/*Add Author*/
var validateAddAuthor = function () {
    var bool = true;

    var form = document.getElementById("addauthorform");
    var authorFullname = form.elements["authorFullname"].value;

    var messages = document.getElementById("messages");
    messages.innerHTML = "";

    if(authorFullname == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Yazar İsmi Boş Bırakılamaz.";
        bool = false;
    }

    return bool;
}

/*Add Book*/
var validateAddBook = function () {
    var bool = true;

    var form = document.getElementById("addbookform");
    var bookName = form.elements["bookName"].value;
    var isbnNo = form.elements["isbnNo"].value;
    var serialName   = form.elements["serialName"].value;

    var messages = document.getElementById("messages");
    messages.innerHTML = "";

    if(bookName == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Kitap İsmi Boş Bırakılamaz.";
        bool = false;
    }
    if(isbnNo == ""){
        messages.innerHTML = messages.innerHTML+"<br/>ISBN No Boş Bırakılamaz.";
        bool = false;
    }else{
        if(isbnNo.length != 13){
            messages.innerHTML = messages.innerHTML+"<br/>ISBN 13 Haneli olmalıdır.";
            bool = false;
        }
    }
    if(serialName == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Seri No Boş Bırakılamaz.";
        bool = false;
    }else{
        if(serialName < 3){
            messages.innerHTML = messages.innerHTML+"<br/>Seri En Az 3 Haneli Olmalı.";
            bool = false;
        }
    }

    return bool;
}

/*Add Publisher*/
var validateAddPublisher = function () {
    var bool = true;

    var form = document.getElementById("addpublisherform");
    var publisherName = form.elements["publisherName"].value;

    var messages = document.getElementById("messages");
    messages.innerHTML = "";

    if(publisherName == ""){
        messages.innerHTML = messages.innerHTML+"<br/>Yayıncı İsmi Boş Bırakılamaz.";
        bool = false;
    }

    return bool;
}
