package com.mkm.yroki;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by papa on 27.03.2022
 */

public class Person implements Serializable {

    static private final long serialVersionUID = -1374686876597396327L;

    private String name;
    private String surname;
    private String sex;
    private int age;
    transient private Company company;

    List<Ticket> tickets = new ArrayList<>();

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", tickets=" + tickets +
                '}';
    }
}

class Ticket implements  Serializable{

    String id;
    String title;

    public Ticket(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

class Company {

}