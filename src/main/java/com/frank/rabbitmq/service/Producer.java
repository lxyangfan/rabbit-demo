package com.frank.rabbitmq.service;

import static com.frank.rabbitmq.config.RabbitMqConfiguration.DIRECT_ROUTING_KEY;
import static com.frank.rabbitmq.config.RabbitMqConfiguration.EXCHANGE_NAME;

import com.frank.rabbitmq.model.dto.UserDto;
import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

  @Resource
  private AmqpTemplate amqpTemplate;


  public void produceMessage(int size) {
    List<UserDto> userList = generateRandomUser(size);
    for (UserDto user : userList) {
      amqpTemplate.convertAndSend(EXCHANGE_NAME, DIRECT_ROUTING_KEY, user);
    }
  }

  private List<UserDto> generateRandomUser(int size) {

    // 随机生成size个用户
    List<UserDto> userList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      UserDto user = new UserDto();
      user.setName("user" + i);
      user.setEmail("user" + i + "@gmail.com");
      user.setAge(20 + i);

      String[] tags = {"tag1", "tag2", "tag3"};
      user.setTags(tags);
      user.setSalary(new BigDecimal("10000.001"));

      List friends = new ArrayList<Map>();
      for (int j = 0; j < 3; j++) {
        friends.add(Map.of("name", "friend" + j, "age", 20 + j));
      }
      user.setFriends(friends);
      userList.add(user);
    }
    return userList;

  }


}
