package com.frank.rabbitmq;

import com.frank.rabbitmq.service.Producer;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class RabbitMqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqDemoApplication.class, args);
	}


	@Component
	static class CmdlineRunner implements org.springframework.boot.CommandLineRunner {
		@Resource
		private Producer producer;
		@Override
		public void run(String... args) throws Exception {
			System.out.println("Hello World");
			producer.produceMessage(10);
		}
	}
}
