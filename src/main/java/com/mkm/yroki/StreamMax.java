package com.mkm.yroki;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by papa on 25.03.2022
 */

public class StreamMax {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static AtomicInteger atomicMax = new AtomicInteger(0);
    static int i = 0;

    public static void main(String[] args) {

        Integer ints = Arrays.asList(
                Arrays.asList(1, 4, 7, 9, 11),
                Arrays.asList(1, 4, 7, 45, 11),
                Arrays.asList(1, 4, 70, 45, 11),
                Arrays.asList(1, 4, 7, 45, 110),
                Arrays.asList(1, 40, 7, 9, 11),
                Arrays.asList(16, 46, 76, 33, 66)
        ).parallelStream().map((l) -> {
            // i++;
            int j = atomicInteger.incrementAndGet();
            System.out.println("count=" + j);
            Integer max = MaxExample.max(l, Integer::compare);

            int currentMax = 0;

//            do {
//                currentMax = atomicMax.get();
//
//            } while (max > currentMax && !atomicMax.compareAndSet(currentMax, max));

            atomicMax.getAndUpdate(cur -> max > cur ? max: cur);
            //i--;
            atomicInteger.decrementAndGet();
            return max;
        }).reduce(Math::max).get();

        System.out.println("final count=" + atomicInteger.get());
        System.out.println("final max=" + atomicMax.get());
        System.out.println(ints);


    }
}
