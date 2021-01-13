package com.rabbitmq_springboot.dlx;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DlxConsumer {

    @Bean
    public TopicExchange ordersExchange(){
        return new TopicExchange("orders.exchange");
    }

    @Bean
    public Queue ordersQueue(){
        Map<String,Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange","dlx.exchange");//声明当前队列绑定的死信交换机
//        params.put("x-dead-letter-routing-key","#");//声明当前队列的死信路由键
        params.put("x-message-ttl",10000);//设置队列消息的超时时间，单位毫秒，超过时间进入死信队列
//        params.put("x-max-length", 10);//生命队列的最大长度，超过长度的消息进入死信队列
        return QueueBuilder.durable("orders.queue").withArguments(params).build();
    }

    @Bean
    public Binding ordersBinding(){
        return BindingBuilder.bind(ordersQueue()).to(ordersExchange()).with("*.order");
    }

    @Bean
    public TopicExchange dlxExchange(){
        return new TopicExchange("dlx.exchange");
    }

    @Bean
    public Queue dlxQueue(){
        return QueueBuilder.durable("dlx.queue").build();
    }

    @Bean
    public Binding dlxBinding(){
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("#");
    }

    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @org.springframework.amqp.rabbit.annotation.Queue("dlx.queue"),
                    exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = "dlx.exchange",type = "topic"),
                    key = "#"
            )
    })
    public void dlxConsumer(String message){
        System.out.println("进入死信队列：" + message);
    }
}
