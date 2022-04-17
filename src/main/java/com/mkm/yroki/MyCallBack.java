package com.mkm.yroki;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyCallBack implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        System.out.println(topic + ": " + message);
        MqttExample.myClient.publish("/Test/Topic2", message.getPayload(), 0, false);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
