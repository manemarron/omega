function callConfigureDatabase() {
    var pw = document.getElementById('pw').value;
    var confirm_pw = document.getElementById('confirm_pw').value;
    if (pw === confirm_pw) {
        var method = 'PUT';
        var target = '/db/api/createDatabase';
        var params = {
            dbName: document.getElementById('dbName').value,
            user: document.getElementById('user').value,
            pw: document.getElementById('pw').value
        };
        AJAX_call(method, target, params, createDatabaseSuccessCallback, onError);
    } else {
        document.getElementById('pw').value = "";
        document.getElementById('confirm_pw').value = "";
        alert("Las contraseñas no coinciden")
    }
}

function configureDatabase(){
    document.getElementById("configureDB_div").style.display = "block";
}
