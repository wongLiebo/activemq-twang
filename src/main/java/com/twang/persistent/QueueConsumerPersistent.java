package com.twang.persistent;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueConsumerPersistent {

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
        //6.创建Consumer
        MessageConsumer consumer = session.createConsumer(queue);
        //7.监听消息
        Message message = consumer.receive(1000);
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());
        }
        //8.关闭链接
        consumer.close();
        session.close();
        connection.close();
        System.out.println("消息接收成功........");


    }


}
