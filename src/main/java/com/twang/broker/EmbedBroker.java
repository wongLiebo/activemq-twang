package com.twang.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * 嵌入broker,运行mq
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {

        BrokerService brokerService =  new BrokerService();

        brokerService.setUseJmx(true); //这玩意儿干嘛的...
        brokerService.addConnector("tcp://127.0.0.1:6262");
        brokerService.start();
        System.out.println("broker启动成功...");


    }



}
