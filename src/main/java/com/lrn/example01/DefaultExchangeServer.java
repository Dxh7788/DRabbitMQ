package com.lrn.example01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * without exchanges,Clients who send messages to queue directly.
 *
 *
 * @author xh.d
 * @date 2018/1/8 16:27
 */
public class DefaultExchangeServer {
    public static final String DEFAULT_QUEUE = "D_QUEUE_LRN";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        /*
        * use default channel,use default exchange
        * */
        Channel channel = connection.createChannel();
        /*
        * non-durable,no-exclusive,non-autodelete,Arguments is null,Just declare queue
        * */
        channel.queueDeclare(DEFAULT_QUEUE,false, false,true,null);
        /*
        * publish
        * */
        channel.basicPublish("",DEFAULT_QUEUE,null,new String("First RabbitMQ msg Made by xh.d").getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
