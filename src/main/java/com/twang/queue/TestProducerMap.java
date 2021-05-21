package com.twang.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TestProducerMap {

    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "testqueue";

    public static void main(String[] args) throws JMSException, IOException {

        //1.创建ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        //2.创建Connection
        Connection connection = factory.createConnection();
        //3.开启链接
        connection.start();
        //4.创建Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目标对象
        Queue queue = session.createQueue("queueName");
        //6.创建Producter
        MessageProducer producer = session.createProducer(queue);
        for(int i=1;i<=10;i++){
            //7.创建消息内容
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("123","为你做不可能的事"+i);
            if(i%2==0){
                mapMessage.setStringProperty("name","为你写诗");
            }
            //8.发送消息
            producer.send(mapMessage);
        }
//        System.in.read();
        //9.关闭链接
        producer.close();

        session.close();
        connection.close();
        System.out.println("消息发送成功........");


    }


}
