package com;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * An application to test a different approach to DTOs.
 */
@SpringBootApplication
public class DTOTest {
  public static void main(final String... args) {
    SpringApplication.run(DTOTest.class, args);
  }

  @Bean
  public GraphQLScalarType dateScalarType() {
    return ExtendedScalars.Date;
  }
}
