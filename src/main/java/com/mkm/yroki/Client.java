package com.mkm.yroki;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by papa on 27.03.2022
 */

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 3000);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject(new Person("Vuvan", "Putler", 69));
        System.out.println("sent1");
        Thread.sleep(10000);
        oos.writeObject(new Person("Anfrej", "Schojgu", 72));
        System.out.println("sent2");
        Thread.sleep(10000);
        oos.writeObject(new Person("Serega", "Lavrik", 73));
        System.out.println("sent3");

        oos.close();

    }
}
