package com.mkm.yroki;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by papa on 29.03.2022
 */

public class FileChannelExample {

    public static void main(String[] args) throws IOException {

        FileChannel input = FileChannel.open(Paths.get("file.txt"),
                StandardOpenOption.READ);

        FileChannel output = FileChannel.open(Paths.get("file2.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 16);

        while (input.read(buffer) != -1) {

            buffer.flip();

//            output.write(buffer);
//            while (buffer.remaining() > 0) {
            output.write(buffer);
//            }

            buffer.compact();
//            buffer.clear();
        }


    }


}
