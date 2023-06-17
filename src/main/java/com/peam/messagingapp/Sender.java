package com.peam.messagingapp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();

        //by default it is set to localhost so no need to set it up
        //factory.set("localhost");
        /*If rabbit MQ is working on a remote server
        You have setHost -> setUsername -> setPassword -> setPort
        To configure the connectionto the server
         */

        Connection connection = null;
        try {
            //getting a connection
            connection = factory.newConnection();
            //create a channel
            Channel channel = connection.createChannel();
            //create a Queue and give a Queue name
            channel.queueDeclare("hello-world",false,false,false,null);

            String message = "This is my fist message Queue, is this real ?";

            //Send a message to the queue when exchange is empty then
            //this is a direct or default exchange
            channel.basicPublish("","hello-world", false,null,message.getBytes());

            System.out.println("Waouh, Message sent!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

    }
}
