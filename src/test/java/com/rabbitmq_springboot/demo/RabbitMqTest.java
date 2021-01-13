package com.rabbitmq_springboot.demo;

import com.rabbitmq_springboot.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDlx() throws InterruptedException {
        rabbitTemplate.convertAndSend("orders.exchange","create.order","创建订单");
        Thread.sleep(20000);
    }

    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.info","user.info消息");
    }

    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs","error","发送info消息");
    }

    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("fanout","","fanout模型");
    }

    @Test
    public void testWork(){
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("work","work模型");
        }
    }

    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello word");
    }
}
