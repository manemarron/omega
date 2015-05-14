var createDatabaseSuccessCallback = function (response) {
    var CreateDBModel = response.CreateDBModel;
    setHtmlOf("db_name",CreateDBModel.dbName);
    setHtmlOf("username_span",CreateDBModel.user);
    setHtmlOf("password_span",CreateDBModel.pw);
    
    setDisplayOf("createDatabase","none");
    setDisplayOf("database","block");
    
    resetCreateDBInputs();
    
    console.log("Exito: createDatabase");
    alert("Se creó la base de datos con éxito.");
};
var deleteDatabaseSuccessCallback = function () {
    setHtmlOf("db_name","");
    setHtmlOf("username_span","");
    setHtmlOf("password_span","");
    
    setDisplayOf("createDatabase","block");
    setDisplayOf("database","none");
    
    console.log("Exito: deleteDatabase");
    alert("Se eliminó la base de datos con éxito");

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
                pw: document.getElementById('pw').value
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
    var target = '/db/api/deleteDatabase';
    AJAX_call(method, target, null, deleteDatabaseSuccessCallback, onError);
}

function configureDatabase() {
    document.getElementById("configureDB_div").style.display = "block";
}
function resetCreateDBInputs() {
    document.getElementById("dbName").value = "";
    document.getElementById("user").value = "";
    document.getElementById("pw").value = "";
    document.getElementById("confirm_pw").value = "";
}

function deleteTable(table){
    var method= "DELETE";
    var target="/db/api/deleteTable/"+table;
    AJAX_call(method,target,null,
        function onSuccess(){
            var li = document.getElementById(table);
            document.getElementById("table_list").removeChild(li);
        },
        onError
    );
}