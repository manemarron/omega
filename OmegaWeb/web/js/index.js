var createDatabaseSuccessCallback = function () {
    AJAX_call('GET', '/UpdateSession', null,
            function () { //onSuccess
                document.getElementById("createDatabase").style.display = "none";
                document.getElementById("database").style.display = "block";
                console.log("Exito: createDatabase");
                alert("Exito: createDatabase");
            },
            onError
            );
};
var deleteDatabaseSuccessCallback = function () {
    AJAX_call('GET', '/UpdateSession', null,
            function () { //onSuccess
                document.getElementById("createDatabase").style.display = "block";
                document.getElementById("database").style.display = "none";
                console.log("Exito: deleteDatabase");
                alert("Exito: deleteDatabase");
            },
            onError
            );
};
var onError = function (params) {
    console.log(params);
    alert(params);
};
function callConfigureDatabase() {
    var pw = document.getElementById('pw').value;
    var confirm_pw = document.getElementById('confirm_pw').value;
    if (pw === confirm_pw) {
        var method = 'PUT';
        var target = '/db/api/createDatabase';
        var params = {
            CreateDBModel: {
                dbName: document.getElementById('dbName').value,
                user: document.getElementById('user').value,
                pw: document.getElementById('pw').value,
                user_id: document.getElementById("user_id").value
            }};
        AJAX_call(method, target, params, createDatabaseSuccessCallback, onError);
    } else {
        document.getElementById('pw').value = "";
        document.getElementById('confirm_pw').value = "";
        alert("Las contraseñas no coinciden")
    }
}
function callDeleteDatabase() {
    var method = 'DELETE';
    var target = '/db/api/deleteDatabase/'+document.getElementById("user_id").value;
    AJAX_call(method, target, null, deleteDatabaseSuccessCallback, onError);
}

function configureDatabase() {
    document.getElementById("configureDB_div").style.display = "block";
}
