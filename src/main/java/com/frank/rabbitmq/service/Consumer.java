package com.frank.rabbitmq.service;

import static com.frank.rabbitmq.config.RabbitMqConfiguration.QUEUE_NAME;

import com.frank.rabbitmq.model.dto.UserDto;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

  @RabbitListener(queues = QUEUE_NAME, ackMode = "MANUAL")
  public void consumeMessage(UserDto user, Message message, Channel channel) {
    try {
      int random = (int) (Math.random() * 10);
      if (random / 2 == 0) {
        log.info("Consumer Acknowledge message: {}", user);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
      } else {
        log.info("Consumer Reject message: {}", user);
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
      }
    } catch (Exception e) {
      log.error("Consumer Error: {}", e.getMessage());
    }
  }


}
