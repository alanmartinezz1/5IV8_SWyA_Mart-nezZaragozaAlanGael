//vamos a crear una clase que se encargue de realizar el calculo de 
//los numeros primos y primos relativos del algoritmo RSA
package rsa;

/**
 *
 * @author user
 */

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class RSA_ {

    //variables
    int tamPrimo;
    BigInteger p, q, n;
    BigInteger phi;
    BigInteger e, d;
    BigInteger cifradoOb[];
    String cifradoSt;
    int unu;
    String mensaje;

    //constructor de la clase
    public RSA_(int tamPrimo, String mensaje) {
        this.tamPrimo = tamPrimo;
        this.mensaje = mensaje;
        generarPrimos();
        generarClaves();
        cifradoOb = encriptar(this.mensaje);
    }

    //generar los primos
    public void generarPrimos() {
        p = new BigInteger(tamPrimo, 10, new Random());
        do {
            q = new BigInteger(tamPrimo, 10, new Random());
        } while (q.compareTo(p) == 0);

        
    }

    //generar las claves
    public void generarClaves() {
        

        n = p.multiply(q);

        
        phi = p.subtract(BigInteger.valueOf(1)); //p-1

        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));

        
        do {
            e = new BigInteger(2 * tamPrimo, new Random());
        } while (((e.compareTo(phi)) != -1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));

        
        d = e.modInverse(phi);

    }

    //cifrar
    public BigInteger[] encriptar(String mensaje) {
        //variables
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        
        for (i = 0; i < bigdigitos.length; i++) {
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        
        for (i = 0; i < bigdigitos.length; i++) {
            
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }
        unu = bigdigitos.length;
        return (cifrado);
    }

    //descifrar
    /*public String desencriptar(BigInteger[] cifrado) {

        BigInteger[] descifrado = new BigInteger[cifrado.length];

        
        for (int i = 0; i < descifrado.length; i++) {
            
            descifrado[i] = cifrado[i].modPow(d, n);
        }

        
        char[] charArray = new char[descifrado.length];

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (descifrado[i].intValue());
        }
        return (new String(charArray));
    }
    */
    public static    String desencriptarXD(BigInteger[] cifrado,BigInteger d, BigInteger n) {

        BigInteger[] descifrado = new BigInteger[cifrado.length];

        
        for (int i = 0; i < descifrado.length; i++) {
            
            descifrado[i] = cifrado[i].modPow(d, n);
        }

        
        char[] charArray = new char[descifrado.length];

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (descifrado[i].intValue());
        }
        return (new String(charArray));
    }

    
    public BigInteger damep() {
        return p;
    }

    public BigInteger dameq() {
        return q;
    }

    public BigInteger damephi() {
        return phi;
    }

    public BigInteger damen() {
        return n;
    }

    public BigInteger damee() {
        return e;
    }

    public BigInteger damed() {
        return d;
    }

    public int getUnu() {
        return unu;
    }

    public void setUnu(int unu) {
        this.unu = unu;
    }

}
