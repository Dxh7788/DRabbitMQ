package com.lrn.example01;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xh.d
 * @date 2018/1/8 16:53
 */
public class DefaultExchangeReceiver {
    public static final String DEFAULT_QUEUE = "D_QUEUE_LRN";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        /*
        * use default channel,use default exchange
        * */
        Channel channel = connection.createChannel();
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(DEFAULT_QUEUE, true, consumer);
    }
}
