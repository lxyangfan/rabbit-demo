package com.frank.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

  // 声明 connectionFactory \ rabbitTemplate \ rabbitAdmin \ queue \ exchange \ binding
  // 配置 MessageConverter
  // connectionFactory 通过 配置文件注入： RabbitAutoConfiguration.java)
  // amqpAdmin 也会自动注入(by RabbitAutoConfiguration.java)

  public static final String QUEUE_NAME = "simpleUserDtoQueue";
  public static final String EXCHANGE_NAME = "directUserDtoExchange";
  public static final String DIRECT_ROUTING_KEY = "simple-user-dto-routing-key";

  @Bean
  public Queue simpleUserDtoQueue() {
    // Queue 默认是持久化的，非独占的，非自动删除的
    return new Queue(QUEUE_NAME);
  }

  @Bean
  public Exchange directUserDtoExchange() {
    // Exchange 默认是持久化的，非自动删除的, 不是内部的
    return new DirectExchange(EXCHANGE_NAME);
  }

  @Bean
  public Binding simpleUserDtoBinding() {
    return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME,
        DIRECT_ROUTING_KEY, null);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
