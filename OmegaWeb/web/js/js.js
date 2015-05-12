/**
 * 
 * @param {String} method : HTTP method to be executed
 * @param {String} target : URL of request (On GET requests, must contain query if needed)
 * @param {Object} params : Parameters for POST, PUT and DELETE requests (null if GET)
 * @param {function} onSuccess : Callback function for success
 * @param {function} onError : Callback function for error
 * @returns {undefined}
 */
function AJAX_call(method, target, params, onSuccess, onError) {
    var ajaxRequest;
    if (window.XMLHttpRequest) {
        ajaxRequest = new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
    } else {
        ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
    }
    ajaxRequest.onreadystatechange = function () {
        if (ajaxRequest.readyState === 4 && 
                ajaxRequest.status >= 200 && ajaxRequest.status < 300) {
            if(onSuccess) onSuccess();
        }
        else{
            if(onError) onError();
        }
    }
    var context = "http://localhost:8080/OmegaWeb";
    ajaxRequest.open(method, context+target, true /*async*/);
    ajaxRequest.setRequestHeader("Content-Type", "application/json");
    if (params)
        ajaxRequest.send(JSON.stringify(params));
    else
        ajaxRequest.send();
}

var createDatabaseSuccessCallback = function (){
    
};

var onError = function(params){
    console.log(params);
    alert(params);
};