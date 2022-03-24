package com.mkm.yroki;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by papa on 24.03.2022
 */

public class StreamExample2 {

    public static void main(String[] args) {

//        Stream<Integer> iterate = Stream.iterate(1, i -> i + 1);
//
//        iterate.filter(i -> i % 2 == 0).limit(10).forEach(System.out::println);

        List<String> collect = Arrays.asList("rt", "zz", "ew", "ds").stream().map(i -> i.toUpperCase())
                .collect(Collectors.toList());

        collect.forEach(System.out::println);


        List<Character> collect1 = Arrays.asList("rt", "zz", "ew", "ds").stream()
                .flatMap(i -> i.chars().boxed())
                .map(c -> (char) c.intValue())
                .collect(Collectors.toList());

        System.out.println(collect1);

        Map<Boolean, List<String>> collect2 = Arrays.asList("rt", "zza", "ew", "ds").stream().map(i -> i.toUpperCase())
                .collect(Collectors.partitioningBy(i -> i.contains("A")));

        System.out.println(collect2);

    }


}
