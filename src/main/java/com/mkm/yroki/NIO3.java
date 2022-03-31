package com.mkm.yroki;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by papa on 28.03.2022
 */

public class NIO3 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(3000));

        server.configureBlocking(false);

        server.register(selector, SelectionKey.OP_ACCEPT);


        ByteBuffer buffer = ByteBuffer.allocate(4);

        while (true) {

            int select = selector.select();
            if (select == 0)
                continue;

            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

            while (selectedKeys.hasNext()) {
                SelectionKey key = selectedKeys.next();

                try {

                    if (key.channel() == server) {
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);

                        channel.register(selector, SelectionKey.OP_READ);
                    } else {

//                    channel.read(buffer);

                        ((SocketChannel) key.channel()).read(buffer);
                        buffer.flip();

                        System.out.println("s=" + new String(buffer.array(),
                                buffer.position(), buffer.remaining()));

                        buffer.clear();

//                    Thread.sleep(500);
                    }
                } finally {
                    selectedKeys.remove();
                    ;
                }
            }


        }
    }
}
