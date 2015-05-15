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
                console.log(response);
                var rows = response["SelectResponseModel"].row;
                if (rows === "") {
                }
                else if (rows instanceof Array) {
                    for (var i = 0; i < rows.length; i++) {
                        var column = rows[i].column;
                        var tr = document.createElement("tr");
                        if (column instanceof Array) {
                            for (var j = 0; j < column.length; j++) {
                                var td = document.createElement("td");
                                td.innerHTML = column[j];
                                tr.appendChild(td);
                            }

                        } else {
                            var td = document.createElement("td");
                            td.innerHTML = column;
                            tr.appendChild(td);
                        }
                        document.getElementById("results").appendChild(tr);
                    }

                }
                else {
                    if (rows) {
                        var column = rows.column;
                        var tr = document.createElement("tr");
                        if (column instanceof Array) {
                            for (var j = 0; j < column.length; j++) {
                                var td = document.createElement("td");
                                td.innerHTML = column[j];
                                tr.appendChild(td);
                            }

                        } else {
                            var td = document.createElement("td");
                            td.innerHTML = column;
                            tr.appendChild(td);
                        }
                        document.getElementById("results").appendChild(tr);
                    }
                }
            }
    );
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