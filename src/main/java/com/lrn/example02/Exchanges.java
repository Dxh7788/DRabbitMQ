package com.lrn.example02;

import com.lrn.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xh.d
 * @date 2018/1/9 9:29
 */
public class Exchanges {
    public static final String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";
    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        /**
         * Four queues
         * */
        String d_queue1 = "D_X_QUEUE1";
        String d_queue2 = "D_X_QUEUE2";
        String d_queue3 = "D_X_QUEUE3";
        String d_queue4 = "D_X_QUEUE4";
        Connection connection = ConnectionUtil.getConnection();
        /**
         * firstly,just use one channel
         * */
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(d_queue1, false, false, false, null);
        channel.queueDeclare(d_queue2, false, false, false, null);
        channel.queueDeclare(d_queue3, false, false, false, null);
        channel.queueDeclare(d_queue4, false, false, false, null);
        channel.queueBind(d_queue1, DIRECT_EXCHANGE, "");
        channel.queueBind(d_queue2, DIRECT_EXCHANGE, "");
        channel.queueBind(d_queue3, TOPIC_EXCHANGE, "");
        channel.queueBind(d_queue4, TOPIC_EXCHANGE, "");

        /**
         * verify different queue filling messages via different exchange
         * */
        channel.basicPublish(DIRECT_EXCHANGE, "", null, new String("This is direct_exchanges").getBytes("UTF-8"));
        channel.basicPublish(TOPIC_EXCHANGE, "", null, new String("This is topic_exchanges").getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
