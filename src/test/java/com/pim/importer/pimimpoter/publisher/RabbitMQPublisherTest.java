package com.pim.importer.pimimpoter.publisher;

import com.pim.importer.pimimpoter.config.ApplicationConfigReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RabbitMQPublisherTest
{

  @InjectMocks
  private RabbitMQPublisher rabbitMQPublisher;

  @Mock
  private AmqpTemplate amqpTemplate;

  @Mock
  private ApplicationConfigReader applicationConfig;

  @Test
  public void publishMessage()
  {
    final String message = "hello";
    when(applicationConfig.getPimExchange()).thenReturn("testExchange");
    when(applicationConfig.getPimRoutingKey()).thenReturn("testRoutingKey");
    rabbitMQPublisher.publishMessage(message);

    verify(amqpTemplate, times(1)).convertAndSend(anyString(), anyString(), eq(message));
  }
}