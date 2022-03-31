package com.mkm.yroki;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by papa on 27.03.2022
 */

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        ServerSocket server = new ServerSocket(3000);

//        Socket socket = new Socket("localhost", 3000);

        ExecutorService tp = Executors.newFixedThreadPool(2);
        Semaphore semaphore = new Semaphore(2);

        while (true) {

            semaphore.acquire();
            Socket accept1 = server.accept();
//            new Thread(() -> {
            tp.execute(() -> {
                try (Socket accept = accept1) {

                    serve(accept);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
//        }).start();
        }
    }

    private static void serve(Socket accept) throws IOException, ClassNotFoundException {
        InputStream inputStream;
        inputStream = accept.getInputStream();

        ObjectInputStream ois = new ObjectInputStream(inputStream);

        while (true) {
            Object object = ois.readObject();
            System.out.println(object);
        }


//        OutputStream outputStream = accept.getOutputStream();

//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf8"));
//
//        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//
//        for (int i = 0; i < 10; i++) {
//            String readLine = br.readLine();
//            oos.writeUTF("OK");
//            oos.flush();
//            System.out.println("line=" + readLine);
//        }
//        accept.close();
    }
}
