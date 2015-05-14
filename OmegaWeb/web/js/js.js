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
        if (ajaxRequest.readyState === 4) {
            setDisplayOf("loadingDiv", "none");
            if (ajaxRequest.status >= 200 && ajaxRequest.status < 300) {
                if (onSuccess){
                    var x2js = new X2JS();
                    if(ajaxRequest.responseXML){
                        var responseBody = x2js.xml2json(ajaxRequest.responseXML);
                        onSuccess(responseBody);
                    } else{
                        onSuccess();
                    }
                }
            }
            else {
                if (onError)
                    onError(target);
            }
        }
    }
    setDisplayOf("loadingDiv", "block");
    var context = "http://localhost:8080/OmegaWeb";
    ajaxRequest.open(method, context + target, true /*async*/);
    ajaxRequest.setRequestHeader("Content-Type", "application/xml");
    if (params) {
        var x2js = new X2JS();
        params = x2js.json2xml_str(params);
        console.log(params);
        ajaxRequest.send(params);
    } else
        ajaxRequest.send();
}

function setDisplayOf(id, display){
    document.getElementById(id).style.display = display;
}
function setHtmlOf(id, html){
    document.getElementById(id).innerHTML = html;
}