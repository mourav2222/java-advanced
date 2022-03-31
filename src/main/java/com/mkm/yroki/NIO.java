package com.mkm.yroki;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by papa on 28.03.2022
 */

public class NIO {

    public static void main(String[] args) throws Exception {

//        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12);

        byteBuffer.put("qwerty".getBytes("cp1251"));
//        byteBuffer.put((byte) 0x7);

        byteBuffer.flip();
        byteBuffer.get();
        byteBuffer.get();
        byteBuffer.get();
        byteBuffer.get();

        byteBuffer.compact();

        System.out.println("buffer=" +byteBuffer );

        byteBuffer.clear();
    }
}
