package com.frank.rabbitmq.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String email;
    private Integer age;

    private String[] tags;
    private List<Map> friends;
    private BigDecimal salary;


}
