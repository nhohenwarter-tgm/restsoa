window.onload = function(){
    var search = parse("search");
    if(search != null) {
        $("#search").val(search.substring(1, search.length-1));
    }
    if(sessionStorage.getItem("ok") != null){
        sessionStorage.removeItem("ok");
        $("#alert_success").show(1000);
        setTimeout(function(){
            $("#alert_success").hide(1000);
        }, 5000);
    }
    if(sessionStorage.getItem("failed") != null){
        sessionStorage.removeItem("failed");
        $("#alert_error").show(1000);
        setTimeout(function(){
            $("#alert_error").hide(1000);
        }, 5000);
    }
};

function toMainPage() {
    window.location.href = "/";
    return false;
}

function saveNew(email, bio){
    $.ajax({
        url: "/api/add",
        method: "POST",
        data: JSON.stringify({email: email,bio: bio}),
        contentType: "application/json",
        processData: false,
        dataType: "json",
        async:false,
        success: function(data) {
            if(data['success']){
                sessionStorage.setItem("ok", true);
                window.location.href = "/";
            }else{
                sessionStorage.setItem("failed", true);
                window.location.href = "/";
            }
        },
        error: function(data) {
            sessionStorage.setItem("failed", true);
            window.location.href = "/";
        }
    });
    return false;
}

function saveChanges(email, bio){
    $.ajax({
        url: "/api/edit",
        method: "PUT",
        data: JSON.stringify({email: email,bio: bio}),
        contentType: "application/json",
        processData: false,
        dataType: "json",
        async:false,
        success: function(data) {
            if(data['success']){
                sessionStorage.setItem("ok", true);
                window.location.href = "/";
            }else{
                sessionStorage.setItem("failed", true);
                window.location.href = "/";
            }
        },
        error: function(data) {
            sessionStorage.setItem("failed", true);
            window.location.href = "/";
        }
    });
    return false;
}

function showEditPage(email){
    window.location.href = "/edit/'"+email+"'";
}

function deletePerson(email) {
    $.ajax({
        url: "/api/delete/'"+email+"'",
        method: "DELETE",
        dataType: "json",
        async:false,
        success: function(data) {
            if(data['success']){
                sessionStorage.setItem("ok", true);
                window.location.reload();
            }else{
                sessionStorage.setItem("failed", true);
                window.location.reload();
            }
        },
        error: function(data) {
            sessionStorage.setItem("failed", true);
            window.location.reload();
        }
    });
    return false;
}

function paginationNext(){
    var currentPage = parse("page");
    var search = parse("search");
    if(search != null){
        window.location.href = "/search="+search+"/page="+(parseInt(currentPage)+1);
    }else{
        window.location.href = "/page="+(parseInt(currentPage)+1);
    }
}

function paginationPrev(){
    var currentPage = parse("page");
    var search = parse("search");
    if(search != null){
        window.location.href = "/search="+search+"/page="+(parseInt(currentPage)-1);
    }else{
        window.location.href = "/page="+(parseInt(currentPage)-1);
    }
}

function search(search){
    if(search.length <= 0){
        window.location.href = "/page=1";
    }else {
        window.location.href = "/search='" + search + "'/page=1";
    }
}

function parse(val) {
    var result = null,
        tmp = [];
    window.location.href
        .substr(1)
        .split("/")
        .forEach(function (item) {
            tmp = item.split("=");
            if (tmp[0] === val) result = decodeURIComponent(tmp[1]);
        });
    return result;
}