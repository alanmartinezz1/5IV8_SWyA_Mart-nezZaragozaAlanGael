"use strict";

var app = new function () {

    /* 
     * Maneja las entradas y salidas del HTML según la encriptación y desencriptación del cifrado.
     * Esta es el único punto de entrada y salida de una función llamada desde el HTML.
     */
    
    this.doCrypt = function (isDecrypt) {
        var keyStr = document.getElementById("key").value;
        if (keyStr.length == 0) {
            alert("La llave está vacía.");
            return;
        }

        var keyArray = filterKey(keyStr);
        if (keyArray.length == 0) {
            alert("La llave no contiene letras.");
            return;
        }

        if (isDecrypt) {
            for (var i = 0; i < keyArray.length; i++)
                keyArray[i] = (26 - keyArray[i]) % 26;
        }

        var textElem = document.getElementById("text");
        textElem.value = crypt(textElem.value, keyArray);
        if (textElem.length == 0) {
            alert("Ingresa texto.");
            return;
        }
    };


    /* 
     * Regresa el resultado de la encriptación según el texto y la llave .
     */
    function crypt(input, key) {
        var output = "";
        for (var i = 0, j = 0; i < input.length; i++) {
            var c = input.charCodeAt(i);
            if (isUppercase(c)) {
                output += String.fromCharCode((c - 65 + key[j % key.length]) % 26 + 65);
                j++;
            } else if (isLowercase(c)) {
                output += String.fromCharCode((c - 97 + key[j % key.length]) % 26 + 97);
                j++;
            } else {
                output += input.charAt(i);
            }
        }
        return output;
    }


    /* 
     * Regresa un array de números, cada uno en un rango de [0, 26), en representación de la llave.
     * La llave pasa por alto cualquier caracter que no sea letra.
     * Ejemplo:
     * - filterKey("AAA") = [0, 0, 0].
     * - filterKey("abc") = [0, 1, 2].
     * - filterKey("the $123# EHT") = [19, 7, 4, 4, 7, 19].
     */
    function filterKey(key) {
        var result = [];
        for (var i = 0; i < key.length; i++) {
            var c = key.charCodeAt(i);
            if (isLetter(c))
                result.push((c - 65) % 32);
        }
        return result;
    }


    // Comprueba que el caracter dado sea una letra del alfabeto.
    function isLetter(c) {
        return isUppercase(c) || isLowercase(c);
    }

    // Comprueba que el caracter dado sea una letra mayúscula.
    function isUppercase(c) {
        return 65 <= c && c <= 90;  // 65 refiere a 'A'. 90 es 'Z'.
    }

    // Comprueba que el caracter dado sea una letra minúscula.
    function isLowercase(c) {
        return 97 <= c && c <= 122;  // 97 refiere a 'a'. 122 es 'z'.
    }

};