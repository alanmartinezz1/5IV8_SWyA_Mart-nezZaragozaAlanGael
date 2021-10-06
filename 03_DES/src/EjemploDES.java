/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */


import java.io.*;

/*vamos a crear un programa que se encargue de poder cifrar un archvo de texto a través del cifrado des utilizando las librerías de crypto y security de java para
atender los cifrados simétricos y asimétricos. Vamos a recibir como parametro un archivo txt y una clave.*/

import javax.crypto.*;

import javax.crypto.spec.*;  //genera llaves del cifrado

import java.security.*;  // trae las instancias del tipo de cifrado

public class EjemploDES {
    
    public static void main(String[] args) throws Exception{
        
        /*Lo primero es comprobar si tenemos cargado el fichero o archivo de texto que vamos a cifrar*/

        if(args.length != 1){
            mensajeAyuda();
            System.exit(1);
        }
        
        /*Cargar el tipo de instancia o proveedor del cfrado simétrico que se va a utilizar*/
        
        System.out.println("1.- Generar la clave DES");
        //la llave la podemos crear a partirde una función genérica llamada funcion hash MD5
        //es una secuanecia de numeros pseudoaleatorios
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        
        //inicializar la llave
        //llave DES 56 bits
        
        generadorDES.init(56);
        
        //vamos a generar la llave secreta para que entre al proceso iterativo de rondas
        //en el proceso del DES entra a una entapa de generación de llaves, entra a 16 rondas con subllaves, 
        //esta es para generar esas subllaves.
        SecretKey subllave = generadorDES.generateKey();
        
        System.out.println("Clave: " + subllave);
        
        //vamos a crear las subllaves a su formato binario
        mostrarBytes(subllave.getEncoded());
        
        System.out.println("");
        
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
        
        /*Todos los bloques de 64 bits quye se formen para cifrar con des, si el bloque 
        no alcanza a llenarse con texto plano, pueda aplicar relleno para completar el bloque
        
        Algoritmo: DES
        Modo: ECB
        Relleno: PKCS5
        
        */
        
        System.out.println("2.- Cifrar con DES el fichero " + args[0]+ ", dejar el resultado en : "
        + args[0]+".cifrado");
        
        cifrado.init(Cipher.ENCRYPT_MODE, subllave);
        
        //leer el archivo fichero y tener el buffer para la lectura, el tamaño y que entren hasta terminar de leer el tamaño del archivo
        
        //el archivo o fichero lo transformamos a bits y hay que cifrar/decifrar segun sea el caso
        
        byte[] buffer = new byte[1000]; //quiero ir letendo de 1000 en 100 bits
        
        byte[] bufferCifrado; //aquí se almacena el resultado
        
        FileInputStream in = new FileInputStream(args[0]);
        
        FileOutputStream out = new FileOutputStream(args[0]+".cifrado");
        
            int bytesleidos = in.read(buffer,0, 1000);
        
        while(bytesleidos != -1){
            //mientras no se llegue al final del archivo/fichero
            
            bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
            
            //para el texto claro leer y enviarlo al buffer cifrado
            out.write(bufferCifrado);
            //escribir el archivo cifrado
            
            bytesleidos = in.read(buffer, 0, 1000);
            
        }
        
        //acompletar el fichero/archivo con el cifrado
        
        bufferCifrado = cifrado.doFinal();
        
        out.write(bufferCifrado); //escribir el final del texto cifrado si lo hay
        
        in.close();
        out.close();
        
        System.out.println("3.- Descifrar con DES el fichero" +args[0]+".cifrado"
                +",dejar el resultado en: " +args[0]+"descifrado");
        
        //vamos a descifrar
        cifrado.init(Cipher.DECRYPT_MODE, subllave);
        
        byte[] bufferPlano; //aqui voy almacenar el resultado descifrado
        
         in = new FileInputStream(args[0]+".cifrado");
        
         out = new FileOutputStream(args[0]+".descifrado");
        
        bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            //mientras no se llegue al final del archivo o fichero
            bufferPlano = cifrado.update(buffer, 0, bytesleidos);
            //para el texto claro leer y enviarlo al buffer cifrado
            out.write(bufferPlano);
            //escribir el archivo cifrado
            bytesleidos = in.read(buffer, 0, 1000);
        }
        //acompletar el fichero o archivo con el descifrado
        bufferPlano = cifrado.doFinal();
        out.write(bufferPlano); //escribir el final del texto descifrado si lo hay
        
        in.close();
        out.close();
        
        
        
        
        
    }

    public static void mensajeAyuda() {
        System.out.println("Ejemplo de un cifrado DES utilizando librerias Crypto y Security");
        System.out.println("Necesita la carga de un archivo en txt, no se te olvide ¬¬ agregarlo");
        System.out.println("Con amor yo");
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
