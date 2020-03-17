$(document).ready(function () {
    $("#hamburger-icon").click(function () {
        if( $("#hamburger-icon").hasClass("shift-right-hamburger")){
            $("#hamburger-icon").addClass("shift-left-hamburger");
            $("#hamburger-icon").removeClass("shift-right-hamburger");
            $('#books').removeClass("booksright");
            $('#authors').removeClass("authorsright");
            $('#authordetail').removeClass("authorsright");
            $(".menu").addClass("disabled");
        }
        else{
            $("#hamburger-icon").removeClass("shift-left-hamburger");
            $("#hamburger-icon").addClass("shift-right-hamburger");
            $('#books').addClass("booksright");
            $('#authors').addClass("authorsright");
            $('#authordetail').addClass("authorsright");
            $(".menu").removeClass("disabled");
        }
    })
})