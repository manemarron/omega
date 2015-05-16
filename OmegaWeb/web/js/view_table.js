/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getRows(table_name) {
    var object = {
        SelectRequestModel: {
            table_name: table_name,
            selectColumnNames: {
                column: []
            },
            whereColumnNames: {
                column: []
            },
            values: {
                column: []
            }
        }
    };
    for (var i = 0; i < document.columns.length; i++) {
        object.SelectRequestModel.selectColumnNames.column.push(document.columns[i]);
    }
    AJAX_call("POST", "/db/api/select", object,
            function (response) {
                document.valores_tabla = [];
                console.log(response);
                var rows = response["SelectResponseModel"].row;
                if (rows === "") {
                }
                else if (rows instanceof Array) {
                    for (var i = 0; i < rows.length; i++) {
                        var column = rows[i].column;
                        var tr = document.createElement("tr");
                        if (column instanceof Array) {
                            var arr = [];
                            for (var j = 0; j < column.length; j++) {
                                arr.push(column[j]);
                                //var td = document.createElement("td");
                                //td.innerHTML = column[j];
                                //tr.appendChild(td);
                            }
                            document.valores_tabla.push(arr);

                        } else {
                            var arr = [];
                            arr.push(column);
                            document.valores_tabla.push(arr);
//                            var td = document.createElement("td");
//                            td.innerHTML = column;
//                            tr.appendChild(td);
                        }
                        //document.getElementById("results").appendChild(tr);
                    }

                }
                else {
                    if (rows) {
                        var column = rows.column;
                        var tr = document.createElement("tr");
                        if (column instanceof Array) {
                            var arr = [];
                            for (var j = 0; j < column.length; j++) {
                                arr.push(column[j]);
//                                var td = document.createElement("td");
//                                td.innerHTML = column[j];
//                                tr.appendChild(td);
                            }
                            document.valores_tabla.push(arr);

                        } else {
                            var arr = [];
                            arr.push(column);
                            document.valores_tabla.push(arr);
//                            var td = document.createElement("td");
//                            td.innerHTML = column;
//                            tr.appendChild(td);
                        }
//                        document.getElementById("results").appendChild(tr);

                    }
                }
                setCurrentResult();
                console.log(document.valores_tabla);
            }
    );
}
function setCurrentResult() {
    var tr = document.getElementById("resultsTr");
    var tds = tr.getElementsByTagName("td");
    for (var k = 0; k < document.valores_tabla[document.indice].length; k++) {
        tds[k].innerHTML = document.valores_tabla[document.indice][k];
    }
}

function validateAddRow(table_name) {
    var object = {
        AddRowModel: {
            table_name: table_name,
            values: parseQuery()
        }
    };
    AJAX_call("POST", "/db/api/addRow", object,
            function () {
                document.indice=document.valores_tabla.length;
                getRows(table_name);
                resetForm();
            }
    );
    return false;
}
function parseQuery() {
    var form = document.getElementsByTagName("form");
    var columns = form[0].getElementsByTagName("span");
    var object = {value: []};
    for (var i = 0; i < columns.length; i++) {
        var input = columns[i].getElementsByTagName("input")[0];
        object.value.push(input.value);
    }
    return object;
}
function resetForm() {
    var columns = document.getElementsByTagName("span");
    for (var i in columns) {
        var input = columns[i].getElementsByTagName("input")[0];
        input.value = "";
    }
}