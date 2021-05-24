package com.twang.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ProducerTransaction {

    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "testqueue";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("ProducerTransaction");
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("为你写诗为你静止");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送成功........");


    }


}
