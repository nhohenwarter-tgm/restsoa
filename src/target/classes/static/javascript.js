window.onload = function(){
    var search = parse("search");
    if(search != null) {
        $("#search").val(search);
    }
    if(localStorage.getItem("success") != null){
        localStorage.removeItem("success");
        $("#alert_success").show(1000);
        setTimeout(function(){
            $("#alert_success").hide(1000);
        }, 5000);
    }
    if(localStorage.getItem("error") != null){
        localStorage.removeItem("error");
        $("#alert_error").show(1000);
        setTimeout(function(){
            $("#alert_error").hide(1000);
        }, 5000);
    }
};

function saveNew(email, bio){
    $.ajax({
        url: "/api/add",
        method: "POST",
        data: JSON.stringify({email: email,bio: bio}),
        contentType: "application/json",
        processData: false,
        success: function(data) {
            if(data == true){
                localStorage.setItem("success", true);
                location.href = "/";
            }else{
                localStorage.setItem("error", true);
                location.href = "/";
            }
        },
        error: function(data) {
            localStorage.setItem("error", true);
            location.href = "/";
        }
    });
}

function deletePerson(email) {
    $.ajax({
        url: "/api/delete/"+email,
        method: "DELETE",
        success: function(data) {
            if(data == true){
                localStorage.setItem("success", true);
                location.href = location.href;
            }else{
                localStorage.setItem("error", true);
                location.href = location.href;
            }
        },
        error: function(data) {
            localStorage.setItem("error", true);
            location.href = location.href;
        }
    });
}

function paginationNext(){
    var currentPage = parse("page");
    var search = parse("search");
    if(search != null){
        location.href = "/search="+search+"/page="+(parseInt(currentPage)+1);
    }else{
        location.href = "/page="+(parseInt(currentPage)+1);
    }
}

function paginationPrev(){
    var currentPage = parse("page");
    var search = parse("search");
    if(search != null){
        location.href = "/search="+search+"/page="+(parseInt(currentPage)-1);
    }else{
        location.href = "/page="+(parseInt(currentPage)-1);
    }
}

function search(search){
    if(search.length <= 0){
        location.href = "/page=1";
    }else {
        location.href = "/search=" + search + "/page=1";
    }
}

function parse(val) {
    var result = null,
        tmp = [];
    location.href
        .substr(1)
        .split("/")
        .forEach(function (item) {
            tmp = item.split("=");
            if (tmp[0] === val) result = decodeURIComponent(tmp[1]);
        });
    return result;
}