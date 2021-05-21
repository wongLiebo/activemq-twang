package com.twang.queue;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 异步消费dmeo
 */
public class TestConsumerAsync {

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
        //6.创建Consumer
        MessageConsumer consumer = session.createConsumer(queue);
        //7.异步监听消息
        consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                }if(message instanceof MapMessage ){
                    MapMessage mapMessage = (MapMessage) message;
                    System.out.println( mapMessage.getString("123")+"---"+mapMessage.getStringProperty("name"));
                }if(message instanceof ObjectMessage){
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    System.out.println(objectMessage.getObject().toString());
                }
            }
        });
        System.in.read();
        //8.关闭链接
//        consumer.close();
//        session.close();
//        connection.close();
//        System.out.println("消息接收成功........");


    }


}
