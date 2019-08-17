package com.pim.importer.pimimpoter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pim.importer.pimimpoter.publisher.RabbitMQPublisher;
import org.apache.commons.csv.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.util.*;

@SpringBootApplication
public class PimImportApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(PimImportApplication.class, args);
  }

  @Bean
  CommandLineRunner init(RabbitMQPublisher rabbitMQPublisher)
  {
    return args -> {
      InputStream inputStream = PimImportApplication.class.getClassLoader().getResourceAsStream("data/dataExample.csv");
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(br);
      final Map<String, Integer> headerMap = parser.getHeaderMap();
      final List<CSVRecord> records = parser.getRecords();

      for (CSVRecord csvRecord : records)
      {
        Map<String, Object> result = new HashMap<>();
        for (String header : headerMap.keySet())
        {
          result.put(header, csvRecord.get(header));
        }
        rabbitMQPublisher.publishMessage(new ObjectMapper().writeValueAsString(result));
      }
    };
  }

}
