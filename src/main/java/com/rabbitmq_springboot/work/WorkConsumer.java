package com.rabbitmq_springboot.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void workConsumer1(String message){
        System.out.println("message1=" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void workConsumer2(String message){
        System.out.println("message2=" + message);
    }
}
