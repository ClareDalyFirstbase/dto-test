package com.api;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.data.S3File;
import com.service.PersonDTO;
import com.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

@Component
@AllArgsConstructor
@Validated
@Slf4j
public class PersonMutationResolver implements GraphQLMutationResolver {
  private final PersonService personService = new PersonService();

  /**
   * Creates a new person and returns a public Person
   * @param createPerson the person to create
   * @param picture the file name of the picture (for testing)
   * @return a public person
   */
  public PersonDTO.Read.Public createPublicPerson(
          @Valid final PersonDTO.Write.Create createPerson, final String picture) {
    if (Objects.nonNull(picture)) {
      createPerson.setPicture(new S3File(picture));
    }

    PersonDTO.Read.Public response = personService.createPersonPublic(createPerson);
    log.debug("Created " + PersonDTO.getFullName(response));
    return response;
  }

  /**
   * Creates a new person and returns a private Person
   * @param createPerson the person to create
   * @param picture the file name of the picture (for testing)
   * @return a private person
   */
  public PersonDTO.Read.Private createPrivatePerson(
          @Valid final PersonDTO.Write.Create createPerson, final String picture) {
    if (Objects.nonNull(picture)) {
      createPerson.setPicture(new S3File(picture));
    }

    PersonDTO.Read.Private response = personService.createPersonPrivate(createPerson);
    log.debug("Created " + PersonDTO.getFullName(response));
    return response;
  }
}
