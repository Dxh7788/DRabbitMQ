package com.lrn.example02;

import com.lrn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xh.d
 * @date 2018/1/9 9:43
 */
public class Consumers {
    public static final String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";
    public static final String TOPIC_EXHCNAGE = "TOPIC_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        String d_queue1 = "D_X_QUEUE1";
        String d_queue2 = "D_X_QUEUE2";
        String d_queue3 = "D_X_QUEUE3";
        String d_queue4 = "D_X_QUEUE4";

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         * Consumer1 get message from DIRECT_EXCHANGE that binded with queues d_queue1 and d_queue2.
         * */
        Consumer consumer01 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body) + "direct");
            }
        };
        channel.basicConsume(d_queue1, consumer01);
        channel.basicConsume(d_queue2, consumer01);
        /**
         * Consumer2 get message from TOPIC_EXCHANGE that binded with queues d_queue3 and d_queue4.
         * */
        Consumer consumer02 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body) + "topic");
            }
        };
        channel.basicConsume(d_queue3, consumer02);
        channel.basicConsume(d_queue4, consumer02);
    }
}
