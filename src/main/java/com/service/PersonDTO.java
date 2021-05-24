package com.service;

import com.data.Person;
import com.data.S3File;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The Person DTO for the service. Uses static classes wrapped in Enums to simulate name spaces.
 * Uses interfaces to ensure consistent syntax across DTOs and make it easier to document fields (if
 * there is any confusion). Validation is shared across implementations as interface methods contain
 * the validation. It can also help create utility methods that are reusable (see getFullName)
 * regardless of the type
 *
 * <p>Downsides include really long implementation lists, but this could perhaps be interfaced out.
 * Converting between DTOs and entities could probably be nicer as well.
 */
public enum PersonDTO {
  ;

  /**
   * Gets the full name from a DTO that contains both a forename and surname
   *
   * @param dto the DTO type
   * @param <DTO> the DTO to get the full name for
   * @return the full name of the person from the DTO
   */
  public static <DTO extends Forename & Surname> String getFullName(DTO dto) {
    return dto.getForename() + " " + dto.getSurname();
  }

  /** The DTOs for read operations */
  public enum Read {
    ;

    /** A "private" readable DTO */
    @Value
    public static class Private
        implements Forename,
            Surname,
            Email,
            SecondaryEmail,
            Slug,
            StartDate,
            PictureUrl {
      String forename;
      String surname;
      String email;
      String secondaryEmail;
      String slug;
      LocalDate startDate;
      String pictureUrl;

      /**
       * Creates a private DTO from a Person entity
       *
       * @param person the person entity
       */
      protected Private(Person person) {
        this.forename = person.getForename();
        this.surname = person.getSurname();
        this.email = person.getEmail();
        this.secondaryEmail = person.getSecondaryEmail();
        this.slug = person.getSlug();
        this.startDate = person.getStartDate();
        this.pictureUrl =
            Objects.nonNull(person.getPicture()) ? person.getPicture().toString() : "Image unavailable";
      }
    }

    /** Creates a "public" readable DTO. Missing picture to show a difference with "private" */
    @Value
    public static class Public
        implements Forename, Surname, Email, SecondaryEmail, Slug, StartDate {
      String forename;
      String surname;
      String email;
      String secondaryEmail;
      String slug;
      LocalDate startDate;

      /**
       * Creates a public DTO from a Person entity
       *
       * @param person the person entity
       */
      protected Public(final Person person) {
        this.forename = person.getForename();
        this.surname = person.getSurname();
        this.email = person.getEmail();
        this.secondaryEmail = person.getSecondaryEmail();
        this.slug = person.getSlug();
        this.startDate = person.getStartDate();
      }
    }
  }

  /** The DTOs for write operations */
  public enum Write {
    ;

    /** A DTO for creating a person */
    @Data
    public static class Create
        implements Forename,
            Surname,
            Email,
            SecondaryEmail,
            Slug,
            StartDate,
            Picture {
      String forename;
      String surname;
      String email;
      String secondaryEmail;
      String slug;
      LocalDate startDate;
      List<Object> packages;
      S3File picture;
      Object organization;
      Object address;
      String status;
      List<String> groups;

      /**
       * Turns the DTO into an entity to save to the repository
       *
       * @return a person to save
       */
      public Person toPerson() {
        Person person = new Person();
        person.setForename(forename);
        person.setSurname(surname);
        person.setEmail(email);
        person.setSecondaryEmail(secondaryEmail);
        person.setSlug(slug);
        person.setStartDate(startDate);
        person.setPicture(picture);
        return person;
      }
    }
  }

  private interface Id {
    UUID getId();
  }

  private interface Forename {
    @NotEmpty
    String getForename();
  }

  private interface Surname {
    @NotEmpty
    String getSurname();
  }

  private interface Email {
    @NotEmpty
    String getEmail();
  }

  private interface SecondaryEmail {
    String getSecondaryEmail();
  }

  private interface Slug {
    String getSlug();
  }

  private interface StartDate {
    LocalDate getStartDate();
  }

  private interface PictureUrl {
    String getPictureUrl();
  }

  private interface Picture {
    S3File getPicture();
  }
}
