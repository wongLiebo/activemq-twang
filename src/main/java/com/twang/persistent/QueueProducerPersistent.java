package com.twang.persistent;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducerPersistent {

    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "testqueue";

    public static void main(String[] args) throws JMSException {

        //1.创建ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        //2.创建Connection
        Connection connection = factory.createConnection();
        //3.开启链接
        connection.start();
        //4.创建Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目标对象
        Queue queue = session.createQueue("QueueConsumerPersistent");
        //6.创建Producter
        MessageProducer producer = session.createProducer(queue);

        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //7.创建消息内容
        TextMessage textMessage = session.createTextMessage("为你写诗为你静止");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭链接
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送成功........");


    }


}
