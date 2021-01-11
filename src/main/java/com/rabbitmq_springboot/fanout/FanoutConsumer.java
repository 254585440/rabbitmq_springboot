package com.rabbitmq_springboot.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue,
                exchange = @Exchange(value = "fanout",type = "fanout")
        )
    })
    public void fanoutConsumer1(String message){
        System.out.println("message1=" + message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue,
                exchange = @Exchange(value = "fanout",type = "fanout")
        )
    })
    public void fanoutConsumer2(String message){
        System.out.println("message2=" + message);
    }
}
