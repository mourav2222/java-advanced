package com.mkm.yroki;

import com.mkm.yroki.NewInterface;

import java.util.Scanner;

public class NewClass2 {


    Scanner s = new Scanner(System.in);

    int nexTInt = s.nextInt();

    NewInterface a2 = () -> System.out.println(nexTInt);

    public NewClass2() {
    }
}
