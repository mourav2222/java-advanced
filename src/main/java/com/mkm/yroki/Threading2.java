package com.mkm.yroki;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by papa on 24.03.2022
 */

class Lock2 {

    volatile boolean locked = false;

    void lock() {
        if (!locked) {
            locked = true;
        } else {

            while (locked) {

                try {
                    Thread.sleep(0, 1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }

        }
    }

    void unlock() {

        locked = false;
    }
}

public class Threading2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(4);

        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1, 4, 7, 9, 11),
                Arrays.asList(1, 4, 7, 45, 11),
                Arrays.asList(1, 4, 70, 45, 11),
                Arrays.asList(1, 4, 7, 45, 110),
                Arrays.asList(1, 40, 7, 9, 11),
                Arrays.asList(16, 46, 76, 33, 66)
        );

//        List<Future<Integer>> futures = lists.stream()
//                .map(list -> es.submit(() -> MaxExample.max(list, Integer::compare)))
//                .collect(Collectors.toList());


//        for (List<Integer> list : lists) {
//           Future<Integer> submit = es.submit(() -> MaxExample.max(list, Integer::compare));
//           futures.add(submit);
//        }
//
//        List<Integer> collect = lists.parallelStream()
//                .map(list -> MaxExample.max(list, Integer::compare))
//                .collect(Collectors.toList());



        Optional<Integer> collect = lists.parallelStream()
                .map(list -> MaxExample.max(list, Integer::compare))
                .reduce((a, b) -> a > b ? a: b);

        System.out.println(collect.get());

//        List<Integer> collect = futures.stream()
//                .map(f -> {
//                    try {
//                        return f.get();
//                    } catch (InterruptedException | ExecutionException e) {
//                        throw new RuntimeException(e);
//                    }
//                }).collect(Collectors.toList());
//        for (Future<Integer> future : futures) {
//            System.out.println(future.get());
//
//        }


//
        System.exit(0);


    }
}
