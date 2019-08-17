package com.pim.importer.pimimpoter.publisher;

import com.pim.importer.pimimpoter.config.ApplicationConfigReader;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher
{
  private final AmqpTemplate amqpTemplate;
  private final ApplicationConfigReader applicationConfig;

  public RabbitMQPublisher(AmqpTemplate amqpTemplate, ApplicationConfigReader applicationConfig)
  {
    this.amqpTemplate = amqpTemplate;
    this.applicationConfig = applicationConfig;
  }

  public void publishMessage(String message)
  {
    amqpTemplate.convertAndSend(applicationConfig.getPimExchange(), applicationConfig.getPimRoutingKey(), message);
  }
}
