package com.pim.importer.pimimpoter.controller;

import com.pim.importer.pimimpoter.publisher.RabbitMQPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductImportController.class)
public class ProductImportControllerTest
{
  @Autowired
  private MockMvc mvc;

  @MockBean
  RabbitMQPublisher rabbitMQPublisher;

  @Test
  public void givenURLWithProductWhenPublishCalledThenReturnOK() throws Exception
  {
    doNothing().when(rabbitMQPublisher).publishMessage(anyString());
    final MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/publish")
            .content(
                    "{\"name\": \"Samsung Lenovo\",\"description\": \"smart phone tablet\",\"provider\": \"Samsung Galaxy\",\"available\": true,\"measurementUnits\": \"PC\"}")
            .contentType(
                    MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
    assertEquals(200, response.getStatus());
  }
}