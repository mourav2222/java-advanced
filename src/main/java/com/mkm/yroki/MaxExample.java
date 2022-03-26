package com.mkm.yroki;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by papa on 24.03.2022
 */

public class MaxExample {

    public static <T extends Comparable<T>> T max(List<? extends T> data,
                                                   Comparator<? super T> cprt)  {

        T max = data.get(0);

        for (int i = 1; i < data.size(); i++) {
            T item = data.get(i);
            // if (item.compareTo(max) > 0 ) {
            if (cprt.compare(item, max) > 0 ) {
                max = item;
            }

        }

        return max;
    }

    public static void main(String[] args) {

        Iterable i;

        List<Integer> asList = Arrays.asList(1, 2, 3, 4);
        // System.out.println("max: " + max(asList));

        List<String> asList1 = Arrays.asList("111", "222");
        max(asList1, (s1, s2) -> s1.length() - s2.length());
        List<? extends CharSequence> ol = asList1;
        for (CharSequence ol1 : ol ) {
            System.out.println(ol1);
        }

        List<String> asList2 = Arrays.asList("1112", "222");
        List<? super String> ol2 = asList2;
        ol2.add("ddd");




    }
}
