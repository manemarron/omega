/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.num_columna = 0;
document.types = ["integer", "decimal", "double", "varchar", "char", "text", "boolean", "timestamp"];
function agregarColumna() {
    var columna = document.createElement("div");
    columna.className = "columna";
    columna.id = "" + document.num_columna;

    //Span para el nombre de la columna
    var nombre = document.createElement("span");
    var nombre_lbl = document.createElement("label");
    nombre_lbl.setAttribute("for", "column_name_" + document.num_columna);
    nombre_lbl.innerHTML = "Nombre de la columna:";
    var nombre_ipt = document.createElement("input");
    nombre_ipt.id = "column_name_" + document.num_columna;
    nombre_ipt.className = "name";
    nombre_ipt.setAttribute("type", "text");

    nombre.appendChild(nombre_lbl);
    nombre.appendChild(nombre_ipt);


    //Span para el tipo de la columna
    var tipo = document.createElement("span");
    var tipo_lbl = document.createElement("label");
    tipo_lbl.setAttribute("for", "column_type_" + document.num_columna);
    tipo_lbl.innerHTML = "Tipo:";
    var tipo_select = document.createElement("select");
    tipo_select.id = "column_type_" + document.num_columna;
    tipo_select.className = "type";
    tipo_select.setAttribute("onchange", "onChangeType(this," + document.num_columna + ");");
    var option = document.createElement("option");
    option.setAttribute("value", "");
    option.setAttribute("selected", true);
    option.innerHTML = "--Selecciona--";
    tipo_select.appendChild(option);
    for (var i = 0; i < document.types.length; i++) {
        var type = document.types[i];
        option = document.createElement("option");
        option.setAttribute("value", type);
        option.innerHTML = type;
        tipo_select.appendChild(option);
    }

    tipo.appendChild(tipo_lbl);
    tipo.appendChild(tipo_select);


    //Span para el tamaño de un campo char o varchar
    var tamano = document.createElement("span");
    tamano.id = "size" + document.num_columna;
    tamano.style.display = "none";
    var tamano_lbl = document.createElement("label");
    tamano_lbl.setAttribute("for", "column_size_" + document.num_columna);
    tamano_lbl.innerHTML = "Tamaño:";
    var tamano_ipt = document.createElement("input");
    tamano_ipt.id = "column_size_" + document.num_columna;
    tamano_ipt.className = "size";
    tamano_ipt.setAttribute("type", "number");
    tamano_ipt.setAttribute("min", "1");
    tamano_ipt.setAttribute("step", "1");
    tamano_ipt.setAttribute("max", "32672");

    tamano.appendChild(tamano_lbl);
    tamano.appendChild(tamano_ipt);


    //Span con checkbox para ver si el campo ser anulable o no
    var nullable = document.createElement("span");
    var nullable_lbl = document.createElement("label");
    nullable_lbl.setAttribute("for", "column_null_" + document.num_columna);
    nullable_lbl.innerHTML = "NULL?";
    var nullable_ipt = document.createElement("input");
    nullable_ipt.id = "column_null_" + document.num_columna;
    nullable_ipt.className = "nullable";
    nullable_ipt.setAttribute("type", "checkbox");
    nullable_ipt.setAttribute("checked", "true");

    nullable.appendChild(nullable_lbl);
    nullable.appendChild(nullable_ipt);

    //Span para ver si el campo entrará dentro de la llave primaria de la tabla.
    var pk = document.createElement("span");
    var pk_lbl = document.createElement("label");
    pk_lbl.setAttribute("for", "column_pk_" + document.num_columna);
    pk_lbl.innerHTML = "PK?";
    var pk_ipt = document.createElement("input");
    pk_ipt.id = "column_pk_" + document.num_columna;
    pk_ipt.className = "pk";
    pk_ipt.setAttribute("type", "checkbox");
    pk_ipt.setAttribute("checked", "true");

    pk.appendChild(pk_lbl);
    pk.appendChild(pk_ipt);


    //Boton para borrar un campo
    var borrar = document.createElement("span");
    var borrar_btn = document.createElement("button");
    borrar_btn.innerHTML = "Borrar columna";
    borrar_btn.setAttribute("type", "button");
    borrar_btn.setAttribute("onclick", "borrarColumna(" + document.num_columna + ");");

    borrar.appendChild(borrar_btn);


    columna.appendChild(nombre);
    columna.appendChild(tipo);
    columna.appendChild(tamano);
    columna.appendChild(nullable);
    columna.appendChild(pk);
    columna.appendChild(borrar)

    document.getElementById("columnas").appendChild(columna);
    document.num_columna += 1;
}
function onChangeType(select, id) {
    var value = select.value;
    if (value.indexOf("char") > -1) {
        document.getElementById("size" + id).style.display = "inline-block";
    } else {
        document.getElementById("size" + id).style.display = "none";
    }
}
function borrarColumna(id) {
    var columna = document.getElementById("" + id);
    if (columna.className === "columna")
        document.getElementById("columnas").removeChild(columna);
}
function validateForm() {
    var form = document.forms["table_form"];
    var table_name = form["table_name"].value;
    if (table_name === null || table_name === "") {
        alert("El nombre de la tabla es obligatorio");
        return false;
    }

    var columns = document.getElementsByClassName("columna");
    if (columns.length === 0) {
        alert("Debe haber al menos una columna");
        return false;
    }
    for (var i = 0; i < columns.length; i++) {
        var column = columns[i];
        var inputs = column.getElementsByTagName("input");
        var size = false;
        var selects = column.getElementsByTagName("select");
        for (var j = 0; j < selects.length; j++) {
            console.log(selects[j].name);
            if (selects[j].value === null || selects[j].value === "") {
                alert("Los datos de alguna columna son incorrectos");
                return false;
            }
            if (selects[j].value.indexOf("char") > -1)
                size = true;
        }
        for (var j = 0; j < inputs.length; j++) {
            if (inputs[j].getAttribute("type") === "checkbox") {
            } else if (inputs[j].value === null || inputs[j].value === "") {
                if (!size && inputs[j].id.indexOf("size") > -1)
                    continue;
                alert("Los datos de alguna columna son incorrectos");
                return false;
            }
        }

    }

    var params = parseQuerytoJSON();
    console.log(params);
    AJAX_call("POST", "/db/api/addTable", params,
            function () { //onSuccess
                document.location = "/OmegaWeb";
            },
            onError()
            )
    return false;
}
function parseQuerytoJSON() {
    var result = {columns: []};
    var columns = document.getElementsByClassName("columna");
    if (columns.length === 0) {
        alert("Debe haber al menos una columna");
        return false;
    }
    for (var i = 0; i < columns.length; i++) {
        var object = {};
        var column = columns[i];
        var inputs = column.getElementsByTagName("input");
        var selects = column.getElementsByTagName("select");
        for (var j = 0; j < selects.length; j++) {
            object["column_type"] = selects[j].value;
        }
        for (var j = 0; j < inputs.length; j++) {
            if (inputs[j].getAttribute("type") === "checkbox") {
                object[inputs[j].className] = inputs[j].checked;
            } else if (inputs[j].getAttribute("type") === "number" &&
                    inputs[j].value !== '') {
                object[inputs[j].className] = parseInt(inputs[j].value);
            } else {
                object[inputs[j].className] = inputs[j].value;
            }
        }
        result.columns.push(object);
    }
    return result;
}