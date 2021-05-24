package com.service;

import com.data.PersonRepository;

public class PersonService {
  PersonRepository repository = new PersonRepository();

  public PersonDTO.Read.Private getPrivatePerson() {
    return new PersonDTO.Read.Private(repository.getPerson());
  }

  public PersonDTO.Read.Public getPublicPerson() {
    return new PersonDTO.Read.Public(repository.getPerson());
  }

  public PersonDTO.Read.Private createPersonPrivate(final PersonDTO.Write.Create createPerson) {
    return new PersonDTO.Read.Private(repository.save(createPerson.toPerson()));
  }

  public PersonDTO.Read.Public createPersonPublic(final PersonDTO.Write.Create createPerson) {
    return new PersonDTO.Read.Public(repository.save(createPerson.toPerson()));
  }
}
