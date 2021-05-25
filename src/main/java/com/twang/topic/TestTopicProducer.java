package com.twang.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 主题生产者
 */
public class TestTopicProducer {

    private static String brokerURL = "tcp://127.0.0.1:6262";

    private static String topicName = "topicName_demo";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = factory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(topicName);

        MessageProducer messageProducer = session.createProducer(topic);

        for (int i = 1; i <=10 ; i++) {
            TextMessage textMessage = session.createTextMessage("topic:"+i);
            messageProducer.send(textMessage);
        }

        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("topic生产者发送成功...");
    }


}
