package com.mkm.yroki;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

public class MqttExample {

    public static MqttAsyncClient myClient;

    public static void main(String[] args) throws MqttException {

       myClient = new MqttAsyncClient(
                "tcp://127.0.0.1:1883", UUID.randomUUID().toString());

       MyCallBack myCallBack = new MyCallBack();
       myClient.setCallback(myCallBack);

        IMqttToken token = myClient.connect();
        token.waitForCompletion();

        myClient.subscribe("/Test/Topic1", 0);



    }

}
