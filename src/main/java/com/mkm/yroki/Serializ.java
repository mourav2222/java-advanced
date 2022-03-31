package com.mkm.yroki;

import java.io.*;

/**
 * Created by papa on 27.03.2022
 */

public class Serializ {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person p = new Person("Vova", "Putkin", 69);
        p.getAge();
        p.setCompany(new Company());

        ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream("dat.ser"));

        p.getTickets().add(new Ticket("1","t1"));
        dos.writeObject(p);
        dos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dat.ser"));

        Person p1 = (Person) ois.readObject();
        System.out.println("p1 = " + p1.toString());
        ois.close();

    }
}


