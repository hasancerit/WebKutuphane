$(document).ready(function () {
    $("#hamburger-icon").click(function () {
        if( $("#hamburger-icon").hasClass("shift-right-hamburger")){
            $("#hamburger-icon").addClass("shift-left-hamburger");
            $("#hamburger-icon").removeClass("shift-right-hamburger");
            $(".menu").addClass("disabled")
        }
        else{
            $("#hamburger-icon").removeClass("shift-left-hamburger");
            $("#hamburger-icon").addClass("shift-right-hamburger");
            $(".menu").removeClass("disabled")
        }
    })
})