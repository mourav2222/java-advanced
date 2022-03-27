package com.mkm.yroki;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by papa on 27.03.2022
 */

public class DataInputExample {

    public static void main(String[] args) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        SecretKeySpec key = new SecretKeySpec("ddffgghhjjkkllss".getBytes("cp1251"), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);

        CipherInputStream cis = new CipherInputStream(null, null);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new CipherOutputStream(new FileOutputStream("out.gz"), cipher), "UTF8"));

//        PrintWriter out = new PrintWriter(new OutputStreamWriter(
//                new GZIPOutputStream(new FileOutputStream("out.gz")), "UTF8"));


        out.println("Priwet mir");
        out.close();

        cipher.init(Cipher.DECRYPT_MODE, key);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new CipherInputStream(new FileInputStream("out.gz"), cipher)));

        System.out.println(bufferedReader.readLine());

//        DataInputStream dataInputStream = new DataInputStream(
//                new BufferedInputStream(
//                        new FileInputStream("file.bin")));

    }
}
