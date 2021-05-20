package com.twang.topic;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 主题生产者
 */
public class TestTopicConsumer {

    private static String brokerURL = "tcp//127.0.0.1:61616";

    private static String topicName = "topicName_demo";

    public static void main(String[] args) throws JMSException, IOException {

        ConnectionFactory factory = new ActiveMQConnectionFactory();

        Connection connection = factory.createConnection();

        connection.setClientID("zhangsan");

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(topicName);

//        MessageConsumer consumer = session.createConsumer(topic);

        //持久状态订阅者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"nidaye");

        topicSubscriber.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("消费者接收到的消息；"+textMessage.getText());
                }
            }
        });

        System.in.read();


    }


}
