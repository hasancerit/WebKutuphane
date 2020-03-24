$(document).ready(function () {
    $("#hamburger-icon").click(function () {
        if( $("#hamburger-icon").hasClass("shift-right-hamburger")){
            $("#hamburger-icon").addClass("shift-left-hamburger");
            $("#hamburger-icon").removeClass("shift-right-hamburger");

            $('#books').removeClass("booksright");
            $('#bookdetail').removeClass("bookright");
            $('#authors').removeClass("divright");
            $('#publishers').removeClass("divright");

            $('#authordetail').removeClass("divright");
            $('#publisherdetail').removeClass("divright");

            $(".menu").addClass("disabled");
        }
        else{
            $("#hamburger-icon").removeClass("shift-left-hamburger");
            $("#hamburger-icon").addClass("shift-right-hamburger");

            $('#books').addClass("booksright");
            $('#bookdetail').addClass("bookright");
            $('#authors').addClass("divright");
            $('#publishers').addClass("divright");

            $('#authordetail').addClass("divright");
            $('#publisherdetail').addClass("divright");

            $(".menu").removeClass("disabled");
        }
    })
})