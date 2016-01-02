window.onload = function(){
    // Suche in URL definiert?
    var search = parse("search");
    if(search != null) {
        // Setzen des Such-Strings aus der URL in das Suchfeld
        $("#search").val(search.substring(1, search.length-1));
    }
    // Success Alert?
    if(sessionStorage.getItem("ok") != null){
        sessionStorage.removeItem("ok");
        // Show
        $("#alert_success").show(1000);
        // Wait & hide
        setTimeout(function(){
            $("#alert_success").hide(1000);
        }, 5000);
    }
    // Error Alert anzeigen?
    if(sessionStorage.getItem("failed") != null){
        sessionStorage.removeItem("failed");
        // Show
        $("#alert_error").show(1000);
        // Wait & hide
        setTimeout(function(){
            $("#alert_error").hide(1000);
        }, 5000);
    }
};

/**
 * Ändert die URL zu "/" und leitet damit auf die Hauptseite weiter
 * @returns {boolean}
 */
function toMainPage() {
    window.location.href = "/";
    return false;
}

/**
 * Schickt ein POST-Request an das Restful Webservice und fügt damit einen neuen Datensatz hinzu
 * @param email die E-Mail Adresse des neuen Eintrags
 * @param bio die Biografie des neuen Eintrags
 * @returns {boolean}
 */
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
                // Operation erfolgreich
                sessionStorage.setItem("ok", true);
                window.location.href = "/";
            }else{
                // Operation fehlgeschlagen
                sessionStorage.setItem("failed", true);
                window.location.href = "/";
            }
        },
        error: function(data) {
            // Operation fehlgeschlagen
            sessionStorage.setItem("failed", true);
            window.location.href = "/";
        }
    });
    return false;
}

/**
 * Schickt ein PUT-Request an das Restful Webservice und verändert damit einen bestehenden Datensatz
 * @param email die E-Mail Adresse des Eintrags
 * @param bio die neue Biografie des Eintrags
 * @returns {boolean}
 */
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
                // Operation erfolgreich
                sessionStorage.setItem("ok", true);
                window.location.href = "/";
            }else{
                // Operation fehlgeschlagen
                sessionStorage.setItem("failed", true);
                window.location.href = "/";
            }
        },
        error: function(data) {
            // Operation fehlgeschlagen
            sessionStorage.setItem("failed", true);
            window.location.href = "/";
        }
    });
    return false;
}

/**
 * Leitet auf "/edit/'<email>'" weiter
 * @param email die E-Mail Adresse für die URL
 */
function showEditPage(email){
    window.location.href = "/edit/'"+email+"'";
}

/**
 * Schickt ein DELETE-Request an das Restful Webservice und löscht damit einen Eintrag aus der Datenbank
 * @param email die E-Mail Adresse des zu löschenden Eintrags
 * @returns {boolean}
 */
function deletePerson(email) {
    $.ajax({
        url: "/api/delete/'"+email+"'",
        method: "DELETE",
        dataType: "json",
        async:false,
        success: function(data) {
            if(data['success']){
                // Operation erfolgreich
                sessionStorage.setItem("ok", true);
                window.location.reload();
            }else{
                // Operation fehlgeschlagen
                sessionStorage.setItem("failed", true);
                window.location.reload();
            }
        },
        error: function(data) {
            // Operation fehlgeschlagen
            sessionStorage.setItem("failed", true);
            window.location.reload();
        }
    });
    return false;
}

/**
 * Leitet auf die nächste Anzeigeseite weiter
 */
function paginationNext(){
    var currentPage = parse("page");
    var search = parse("search");
    if(search != null){
        window.location.href = "/search="+search+"/page="+(parseInt(currentPage)+1);
    }else{
        window.location.href = "/page="+(parseInt(currentPage)+1);
    }
}

/**
 * Leitet auf die vorherige Anzeigeseite weiter
 */
function paginationPrev(){
    var currentPage = parse("page");
    var search = parse("search");
    if(search != null){
        window.location.href = "/search="+search+"/page="+(parseInt(currentPage)-1);
    }else{
        window.location.href = "/page="+(parseInt(currentPage)-1);
    }
}

/**
 * Sucht nach einem String
 * @param search der String, nach dem gesucht werden soll
 */
function search(search){
    if(search.length <= 0){
        window.location.href = "/page=1";
    }else {
        window.location.href = "/search='" + search + "'/page=1";
    }
}

/**
 * Gibt einen Parameter aus der URL zurück
 * @param val der Name des Parameters, der gesucht ist
 * @returns {*}
 */
function parse(val) {
    var result = null, tmp = [];
    window.location.href.substr(1).split("/").forEach(function (item) {
            tmp = item.split("=");
            if (tmp[0] === val) result = decodeURIComponent(tmp[1]);
        });
    return result;
}