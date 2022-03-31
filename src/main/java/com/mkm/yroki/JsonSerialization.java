package com.mkm.yroki;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created by papa on 27.03.2022
 */

public class JsonSerialization {

    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

//        String json = objectMapper.writeValueAsString(
//                new Person("Vovan", "Putler", 69));


        FileOutputStream fout = new FileOutputStream("person.json");
        objectMapper.writeValue( fout,
                new Person("Vovan", "Putler", 69));

        fout.close();

//        Person person = objectMapper.readValue(new File("person.json"), Person.class);

        Map<String, Object> person = objectMapper.readValue(new File("person.json"),
                Map.class);


        System.out.println(person.toString());
        System.out.println(person);

//        System.out.println(json);

    }
}
