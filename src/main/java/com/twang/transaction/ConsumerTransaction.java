package com.twang.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTransaction {

    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "testqueue";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = factory.createConnection();
        connection.start();
        /**
         * 第一个参数：是否开启事务，如果开启必须提交是否，否则此消息会出现重复消费
         * 第二个参数：消息签收方式：自动/手动
         * "事务机制大于ack机制"
         */
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("ProducerTransaction");
        MessageConsumer consumer = session.createConsumer(queue);
        Message message = consumer.receive();
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());
        }
        message.acknowledge();
        consumer.close();
        session.close();
        connection.close();
        System.out.println("消息接收成功........");


    }


}
