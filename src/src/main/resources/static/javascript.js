function saveNew(email, bio){
    $.ajax({
        url: "/add",
        method: "POST",
        data: JSON.stringify({email: email,bio: bio}),
        contentType: "application/json",
        processData: false,
        success: function(data) {
            console.log("Fin: "+data);
        },
        error: function(data) {
            console.log("Err");
        }
    });
}