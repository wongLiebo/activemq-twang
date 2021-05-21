package com.twang.queue;

import com.twang.vo.User;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TestProducerObject {

    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "testqueue";

    public static void main(String[] args) throws JMSException, IOException {

        //1.创建ConnectionFactory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        factory.setTrustAllPackages(true);
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
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setJMSMessageID("objectMessage_"+i);
            objectMessage.setObject(new User("尼古拉斯"+i,10+i,i%2));
            //8.发送消息
            producer.send(objectMessage);
        }
        System.in.read();
        //9.关闭链接
//        producer.close();
//
//        session.close();
//        connection.close();
//        System.out.println("消息发送成功........");


    }


}
