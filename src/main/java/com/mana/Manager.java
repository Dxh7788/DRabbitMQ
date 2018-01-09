package com.mana;

import com.lrn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xh.d
 * @date 2018/1/9 10:14
 */
public class Manager {
    public static final String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";
    public static final String TOPIC_EXHCNAGE = "TOPIC_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
/*        channel.exchangeDelete(DIRECT_EXCHANGE);
        channel.exchangeDelete(TOPIC_EXHCNAGE);*/
        String d_queue1 = "D_X_QUEUE1";
        String d_queue2 = "D_X_QUEUE2";
        String d_queue3 = "D_X_QUEUE3";
        String d_queue4 = "D_X_QUEUE4";
        channel.queueDelete(d_queue1);
        channel.queueDelete(d_queue2);
        channel.queueDelete(d_queue3);
        channel.queueDelete(d_queue4);
        channel.queueDelete("task_queue");
        channel.close();
        connection.close();
    }
}
