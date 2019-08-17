package com.pim.importer.pimimpoter.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfigReader
{
  @Value("${pim.exchange.name}")
  private String pimExchange;

  @Value("${pim.queue.name}")
  private String pimQueue;

  @Value("${pim.routing.key}")
  private String pimRoutingKey;

  public String getPimExchange()
  {
    return pimExchange;
  }

  public String getPimRoutingKey()
  {
    return pimRoutingKey;
  }

  @Bean
  Queue queue()
  {
    return new Queue(pimQueue, true);
  }

  @Bean
  TopicExchange exchange()
  {
    return new TopicExchange(pimExchange);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange)
  {
    return BindingBuilder.bind(queue).to(exchange).with(pimRoutingKey);
  }
}
