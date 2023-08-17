package com.testcase.skyeng.repositories;

import com.testcase.skyeng.models.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CommonRepository<Person> {
    Optional<Person> findByFirstNameAndSecondName(String firstName, String secondName);
    Optional<Person> findByPassportNumber(Long number);
}
