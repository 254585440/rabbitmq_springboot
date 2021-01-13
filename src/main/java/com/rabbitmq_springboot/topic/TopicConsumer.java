package com.rabbitmq_springboot.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(bindings =
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics",type = "topic"),
                    key = {"info.*","user.#"}
            )
    )
    public void TopicConsumer1(String message){
        System.out.println("message1=" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics",type = "topic"),
                    key = {"info.*","product.#"}
            )
    })
    public void TopicConsumer2(String message){
        System.out.println("message2=" + message);
    }
}
