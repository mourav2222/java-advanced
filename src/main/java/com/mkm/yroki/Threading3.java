package com.mkm.yroki;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by papa on 24.03.2022
 */


public class Threading3 {

//    static ExecutorService es = Executors.newFixedThreadPool(4);

    static ForkJoinPool es = new ForkJoinPool(4);


    static class MaxTask extends RecursiveTask<Integer> {

        List<Integer> subList1;

        public MaxTask(List<Integer> subList1) {
            this.subList1 = subList1;
        }

        @Override
        protected Integer compute() {

            int size = subList1.size();
            if (size == 1)
                return subList1.get(0);

            MaxTask maxTask1 = new MaxTask(subList1.subList(0, size / 2));
            MaxTask maxTask2 = new MaxTask(subList1.subList(size / 2, size));

            maxTask1.fork();
            maxTask2.fork();

            Integer j1 = maxTask1.join();
            Integer j2 = maxTask2.join();


            return j1 > j2 ? j1: j2;

        }
    }


    static int max(List<Integer> l) {

        int size = l.size();
        List<Integer> subList1 = l.subList(0, size / 2);
        List<Integer> subList2 = l.subList(size / 2, size);

        Future<Integer> f1 = es.submit(() -> MaxExample.max(subList1, Integer::compare));
        Future<Integer> f2 = es.submit(() -> MaxExample.max(subList2, Integer::compare));

        try {
            return f1.get() > f2.get() ? f1.get() : f2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {


//        List<Future<Integer>> integers = Arrays.asList(
//                        Arrays.asList(1, 4, 7, 9, 11),
//                        Arrays.asList(1, 4, 7, 45, 11),
//                        Arrays.asList(1, 4, 70, 45, 11),
//                        Arrays.asList(1, 4, 7, 45, 110),
//                        Arrays.asList(1, 40, 7, 9, 11),
//                        Arrays.asList(16, 46, 76, 33, 66)
//                ).stream().map((l) -> es.submit(() -> max(l)))
//                .collect(Collectors.toList());

        List<Future<Integer>> integers = Arrays.asList(
                        Arrays.asList(1, 4, 7, 9, 11),
                        Arrays.asList(1, 4, 7, 45, 11),
                        Arrays.asList(1, 4, 70, 45, 11),
                        Arrays.asList(1, 4, 7, 45, 110),
                        Arrays.asList(1, 40, 7, 9, 11),
                        Arrays.asList(16, 46, 76, 33, 66)
                ).stream().map((l) -> es.submit(new MaxTask(l)))
                .collect(Collectors.toList());

        System.out.println(integers.stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()));

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


//        Optional<Integer> collect = lists.parallelStream()
//                .map(list -> MaxExample.max(list, Integer::compare))
//                .reduce((a, b) -> a > b ? a : b);
//
//        System.out.println(collect.get());

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
