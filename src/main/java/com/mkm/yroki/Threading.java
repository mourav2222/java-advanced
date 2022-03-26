package com.mkm.yroki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by papa on 24.03.2022
 */

class Lock {

   volatile boolean locked = false;

    void lock() {
        if(!locked) {
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

public class Threading {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> asList = Arrays.asList(1, 4, 7, 45, 676);
        List<Integer> asList3 = Arrays.asList(16, 46, 76, 456, 66);
        List<String> asList2 = Arrays.asList("dfdfd", "rete", "etbcvb");

        List<Integer> results = new ArrayList<>();

        List<Integer> results2 = Collections.synchronizedList(new ArrayList<Integer>());
//        Lock l = new Lock();

//        ReentrantLock l = new ReentrantLock();

        Thread thread = new Thread(() -> {

            System.out.println(Thread.currentThread().getName());
            Integer max = MaxExample.max(asList, Integer::compare);
//            l.lock();
            synchronized (results) {
                results.add(max);
            }
//            l.unlock();
            System.out.println(max);

        });
        thread.start();


        Thread thread1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName());
            Integer max = MaxExample.max(asList3, Integer::compare);
//            l.lock();
            synchronized (results) {
                results.add(max);
            }
//            l.unlock();
            System.out.println(max);

        });
        thread1.start();


//        Thread thread = new Thread(() ->
//        {
//            String max1 = MaxExample.max(asList2, (s1, s2) ->
//                    (int) (s1.chars().filter(c -> c == 'e').count() -
//                            s2.chars().filter(c -> c == 'e').count())
//            );
//            System.out.println(max1);
//        });
//                thread.start();


        thread.join();
        thread1.join();


        System.out.println(results);

        System.exit(0);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(MaxExample.max(asList, Integer::compare));
//            }
//        }).start();

    }
}
