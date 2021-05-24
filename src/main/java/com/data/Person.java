package com.data;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Person {
  private UUID id;
  private String forename;
  private String surname;
  private String email;
  private String secondaryEmail;
  private String slug;
  private LocalDate startDate;
  private S3File picture;
}
