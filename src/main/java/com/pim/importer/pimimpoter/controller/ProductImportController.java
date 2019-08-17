package com.pim.importer.pimimpoter.controller;

import com.pim.importer.pimimpoter.publisher.RabbitMQPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductImportController
{
  private final RabbitMQPublisher rabbitMQPublisher;

  public ProductImportController(RabbitMQPublisher rabbitMQPublisher)
  {
    this.rabbitMQPublisher = rabbitMQPublisher;
  }

  @RequestMapping(value = "/publish", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity publishMessage(@RequestBody String message)
  {
    rabbitMQPublisher.publishMessage(message);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
