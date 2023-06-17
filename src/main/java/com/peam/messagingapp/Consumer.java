package com.peam.messagingapp;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();

        //by default it is set to localhost so no need to set it up
        //factory.set("localhost");
        /*If rabbit MQ is working on a remote server
        You have setHost -> setUsername -> setPassword -> setPort
        To configure the connection to the server
         */

        Connection connection = null;
        try {
            //getting a connection
            connection = factory.newConnection();
            //create a channel
            Channel channel = connection.createChannel();
            //create a Queue and give a Queue name
            channel.queueDeclare("hello-world",false,false,false,null);

            channel.basicConsume("hello-world", true, (consumerTag, message) ->{
                String readMessage = new String(message.getBody(), "UTF-8");
                System.out.println("Now just consumed a message: "+ readMessage);
            }, consumerTag -> { });
            //The last parameter is a cancel callback
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

    }
}
