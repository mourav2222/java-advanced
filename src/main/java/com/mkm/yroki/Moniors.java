package com.mkm.yroki;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by papa on 25.03.2022
 */

class Resource {

    Object o = null;

    ReentrantLock lock = new ReentrantLock();

    Condition pushed = lock.newCondition();
    Condition polled = lock.newCondition();

    void push(Object o) throws InterruptedException {

        try {
            lock.lock();
            while (this.o != null) {
                polled.await();
            }
            this.o = o;
//        notifyAll();
            pushed.signalAll();
        } finally {
            lock.unlock();
        }


    }

    synchronized Object poll() throws InterruptedException {

        try {
            lock.lock();
            while (this.o == null) {
                pushed.await();
            }
            Object r = o;
            o = null;
//        notifyAll();
            polled.signalAll();
            return r;
        } finally {
            lock.unlock();
        }
    }
}

public class Moniors {
    public static void main(String[] args) {


        Resource r = new Resource();

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("pushing " + i);
                    r.push(i);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        });

        t1.start();


        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    Object poll = r.poll();
                    System.out.println("polled " + poll);

                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        });

        t2.start();
//        Object o = new Object();
//
//        synchronized (o) {
//            o.wait();
//        }
//
//        synchronized (o) {
//            o.notify();
//        }
    }
}
