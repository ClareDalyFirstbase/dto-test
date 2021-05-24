package com.data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PersonRepository {
  /**
   * Gets an example person
   * @return a person
   */
  public Person getPerson() {
    Person person = new Person();
    person.setId(UUID.randomUUID());
    person.setForename("The");
    person.setSurname("Doctor");
    person.setEmail("thedoctor@capitol.gallifrey");
    person.setSecondaryEmail("spacehopper1963@capitol.gallifrey");
    person.setSlug("the-doctor#1234");
    person.setStartDate(LocalDate.of(1963, 11, 23));
    person.setPicture(new S3File(UUID.randomUUID(), "picture", ".jpg"));

    return person;
  }

  /**
   * "Saves" a person
   * @param person the person to save
   * @return the saved person
   */
  public Person save(final Person person) {
    person.setId(UUID.randomUUID());
    return person;
  }
}
