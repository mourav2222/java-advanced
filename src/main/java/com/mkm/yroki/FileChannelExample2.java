package com.mkm.yroki;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by papa on 29.03.2022
 */

public class FileChannelExample2 {

    public static void main(String[] args) throws IOException, InterruptedException {


        AsynchronousFileChannel src = AsynchronousFileChannel.open(Paths.get("file.txt"),
                StandardOpenOption.READ);

        AsynchronousFileChannel taget = AsynchronousFileChannel.open(Paths.get("file2.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);


        copy(src, taget);
        Thread.sleep(10000);

//        ByteBuffer buffer = ByteBuffer.allocate(1024 * 16);
//
//        CompletionHandler<Integer, AsynchronousFileChannel> readHandler = new CompletionHandler<Integer, AsynchronousFileChannel>() {
//            @Override
//            public void completed(Integer result, AsynchronousFileChannel attachment) {
//
//                taget.write(buffer, position, new CompletionHandler<Integer, AsynchronousFileChannel>() {
//                    @Override
//                    public void completed(Integer result, AsynchronousFileChannel attachment) {
//
//                    }
//
//                    @Override
//                    public void failed(Throwable exc, AsynchronousFileChannel attachment) {
//                        exc.printStackTrace();
//                    }
//
//                });
//            }
//
//            @Override
//            public void failed(Throwable exc, AsynchronousFileChannel attachment) {
//                exc.printStackTrace();
//            }
//        };

//        src.read(buffer, 0, taget, readHandler);

    }

    static ByteBuffer buffer = ByteBuffer.allocate(1024 * 16);
    static int position = 0;

    static void copy(AsynchronousFileChannel src, AsynchronousFileChannel taget) {

        src.read(buffer, position, taget, new CompletionHandler<Integer, AsynchronousFileChannel>() {

            @Override
            public void completed(Integer result, AsynchronousFileChannel attachment) {

                buffer.flip();
                taget.write(buffer, position, taget, new CompletionHandler<Integer, AsynchronousFileChannel>() {

                    @Override
                    public void completed(Integer result, AsynchronousFileChannel attachment) {
                        if (result  != -1){
                            position += result;
                            buffer.compact();
                            copy(src, taget);
                        }
                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousFileChannel attachment) {
                        exc.printStackTrace();
                    }

                });
            }

            @Override
            public void failed(Throwable exc, AsynchronousFileChannel attachment) {
                exc.printStackTrace();
            }
        });
    }
}
