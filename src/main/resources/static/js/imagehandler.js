document.getElementById("addbookform").addEventListener('submit',function (ev) {
    var oData = new FormData(document.getElementById("addbookform"));
    var oReq = new XMLHttpRequest();
    oReq.open("POST", "http://localhost:8080/book/addimage", true);
    if(validateAddBook()){
        console.log(oData);
        oReq.send(oData);
    }
    oReq.onreadystatechange = function () {
        if (this.readyState === 4) {
            if ((this.status == 200) && (this.status < 300)) {
                window.location.replace("http://localhost:8080/mainpage");
            }
        }
    }
    ev.preventDefault();
},false);