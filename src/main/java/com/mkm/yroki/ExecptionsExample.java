package com.mkm.yroki;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ExecptionsExample {


    public static void checkPassword(String pwd) throws IllegalAccessException {

        if (!"1234".equals(pwd)) {
            throw new IllegalAccessException("wrong password");
        }

        throw new NullPointerException();

    }


    public static void main(String[] args) throws Exception {

        try (PrintWriter pw = new PrintWriter("out.txt")) {
            pw.println("Hello");
        }

        try {
            //pw.println("Hello");
            checkPassword(null);
        } catch (NullPointerException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finaly");
            //pw.close();
        }
    }

}
