package com.mkm.yroki;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by papa on 27.03.2022
 */

public class EncryptExample {

    public static void main(String[] args) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        KeyPairGenerator pg = KeyPairGenerator.getInstance("RSA");

        KeyPair pair = pg.genKeyPair();
        PublicKey aPublic  = pair.getPublic();
        PrivateKey aPrivate = pair.getPrivate();

//        SecretKeySpec key = new SecretKeySpec("ddffgghhjjkkllss".getBytes("cp1251"), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, aPublic);

        CipherInputStream cis = new CipherInputStream(null, null);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new CipherOutputStream(new FileOutputStream("out.gz"), cipher), "UTF8"));

//        PrintWriter out = new PrintWriter(new OutputStreamWriter(
//                new GZIPOutputStream(new FileOutputStream("out.gz")), "UTF8"));


        out.println("Priwet mir2");
        out.close();

        cipher.init(Cipher.DECRYPT_MODE, aPrivate);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new CipherInputStream(new FileInputStream("out.gz"), cipher)));

        System.out.println(bufferedReader.readLine());


    }
}
