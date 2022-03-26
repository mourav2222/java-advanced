package com.mkm.yroki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by papa on 26.03.2022
 */

public class ConcurrentArrayList {

    public static void main(String[] args) {

//        List<Integer> integers = new ArrayList<>();
        List<Integer> integers = new CopyOnWriteArrayList<>();
        integers.add(3);
        integers.add(3);
        integers.add(3);
        integers.add(3);
        integers.add(3);

        for (Integer integer : integers) {

            System.out.println(integer);
            integers.add(4);
        }


//        HashMap<String, Integer> hm = new HashMap<>();
        Map<String, Integer> hm = new ConcurrentHashMap<>();

        hm.put("sdsd", 1);

        hm.put("htffgf", 5);

    }
}
