package com.mkm.yroki;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * Created by papa on 28.03.2022
 */

public class NIO2 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(12)
                .order(ByteOrder.LITTLE_ENDIAN);

        System.out.println(Arrays.toString(buffer.putInt(256).array()));


        IntBuffer intBuffer = buffer.asIntBuffer();

    }
}
