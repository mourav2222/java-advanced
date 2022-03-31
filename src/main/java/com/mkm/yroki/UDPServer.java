package com.mkm.yroki;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by papa on 28.03.2022
 */

public class UDPServer {

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(3000);

        DatagramPacket datagramPacket = new DatagramPacket(new byte[256], 256);

        while (true) {
            datagramSocket.receive(datagramPacket);

            int length = datagramPacket.getLength();

            String s = new String(datagramPacket.getData(), 0, length);

            System.out.println("string=" + s);

        }

        // Client



    }
}
