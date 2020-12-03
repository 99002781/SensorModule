package io.tpd.rabbitmqexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqExampleApplication {

	
	 public static final String EXCHANGE_NAME = "exchange_name";
	 /*public static final
	 * String DEFAULT_PARSING_QUEUE = "default_parser_q"; public static final String
	 * ROUTING_KEY = "tips";
	 */
	public static final String  Blood_Pressure = "Blood_Pressure";
	public static final String  Blood_oxygen_level = "Blood_oxygen_level";
	public static final String  Heart_rate = "Heart_rate";

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqExampleApplication.class, args);
    }

	/*
	 * @Bean public TopicExchange tipsExchange() { return new
	 * TopicExchange(EXCHANGE_NAME); }
	 * 
	 * @Bean public Queue defaultParsingQueue() { return new
	 * Queue(DEFAULT_PARSING_QUEUE); }
	 * 
	 * @Bean public Binding queueToExchangeBinding() { return
	 * BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(
	 * ROUTING_KEY); }
	 */
    @Bean
	Queue Blood_Pressure() {
		return new Queue("Blood_Pressure", false);
	}

	@Bean
	Queue Blood_oxygen_level() {
		return new Queue("Blood_oxygen_level", false);
	}

	@Bean
	Queue Heart_rate() {
		return new Queue("Heart_rate", false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange("exchange_name");
	}

	@Bean
	Binding Blood_PressureBinding(Queue Blood_Pressure, DirectExchange exchange) {
		return BindingBuilder.bind(Blood_Pressure).to(exchange).with("Blood_Pressure");
	}

	@Bean
	Binding OxygenBinding(Queue Blood_oxygen_level, DirectExchange exchange) {
		return BindingBuilder.bind(Blood_oxygen_level).to(exchange).with("Blood_oxygen_level");
	}

	@Bean
	Binding HeartBinding(Queue Heart_rate, DirectExchange exchange) {
		return BindingBuilder.bind(Heart_rate).to(exchange).with("Heart_rate");
	}
	
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
