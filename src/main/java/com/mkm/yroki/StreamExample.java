package com.mkm.yroki;


import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by papa on 24.03.2022
 */

public class StreamExample {

    public static void main(String[] args) {

        Scanner snr = new Scanner(System.in);

        Stream<String> s = Stream.generate(() -> snr.nextLine());

        Stream<Integer> integerStream = s.filter(s2 -> s2.length() > 3)
                .map(s2 -> s2.length());

        s.filter(s2 -> s2.length() > 3)
                .map(s2 -> s2.toUpperCase())
                .forEach(i -> System.out.println("Priwet " + i));

//       Stream<String> s = Stream.generate(() -> "abc");
//        s.forEach(i -> System.out.println(i));
//        s.forEach(System.out::println);
    }

}
