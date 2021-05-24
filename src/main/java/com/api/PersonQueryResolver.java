package com.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.service.PersonDTO;
import com.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class PersonQueryResolver implements GraphQLQueryResolver {
  private final PersonService personService = new PersonService();

  /**
   * Gets a public person.
   * @return a public person
   */
  public PersonDTO.Read.Public getPublicPerson() {
    PersonDTO.Read.Public response = personService.getPublicPerson();
    log.debug("Got " + PersonDTO.getFullName(response));
    return response;
  }

  /**
   * Gets a private person
   * @return a private person
   */
  public PersonDTO.Read.Private getPrivatePerson() {
    PersonDTO.Read.Private response = personService.getPrivatePerson();
    log.debug("Got " + PersonDTO.getFullName(response));
    return response;
  }
}
