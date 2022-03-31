package com.mkm.yroki;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by papa on 28.03.2022
 */

public class UDPClient {
    public static void main(String[] args) throws Exception {

        byte[] bytes = new byte[] {1, 2, 3, 4};

//        DatagramPacket datagramPacket = new DatagramPacket(new byte[256], 256);

        DatagramPacket datagramPacket = new DatagramPacket(bytes, 4);

        datagramPacket.setAddress(InetAddress.getByName("localhost"));
        datagramPacket.setPort(3000);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(datagramPacket);
    }
}
