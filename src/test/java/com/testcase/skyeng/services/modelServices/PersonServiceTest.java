package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.repositories.AddressRepository;
import com.testcase.skyeng.repositories.PersonRepository;
import org.assertj.core.condition.AnyOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
//@TestPropertySource("/application-test.properties")
//@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PersonServiceTest {
    @Autowired
    private PersonService service;
    @MockBean
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        service = new PersonService(repository);
    }


    @Test
    void checkByPassportTrue() {
        doReturn(Optional.of(TEST_PERSON))
                .when(repository)
                .findByPassportNumber(TEST_PASSPORT);

        Person person = new Person();
        person.setPassportNumber(TEST_PASSPORT);
        Person findPerson = service.checkByPassport(person);

        verify(repository).findByPassportNumber(TEST_PASSPORT);
        verifyNoMoreInteractions(repository);

        assertEquals(TEST_PASSPORT, findPerson.getPassportNumber());
    }

    @Test
    void checkByPassportFalse() {
        Person person = new Person();
        person.setPassportNumber(TEST_PASSPORT);

        doReturn(Optional.empty())
                .when(repository)
                .findByPassportNumber(TEST_PASSPORT);

        doReturn(TEST_PERSON)
                .when(repository)
                .save(person);


        Person findPerson = service.checkByPassport(person);

        verify(repository).findByPassportNumber(TEST_PASSPORT);
        verify(repository).save(person);

        assertEquals(TEST_PASSPORT, findPerson.getPassportNumber());
    }

    @Test
    void addAddress() {
        doReturn(Optional.of(TEST_PERSON))
                .when(repository)
                .findById(TEST_ID);

        doReturn(TEST_PERSON)
                .when(repository)
                .save(any(Person.class));

        Person changePerson = service.addAddress(TEST_ID, TEST_ADDRESS);

        verify(repository).findById(TEST_ID);
        verify(repository).save(any(Person.class));

        assertNotNull(changePerson.getAddress());
        assertEquals(TEST_ADDRESS, changePerson.getAddress());
    }

    @Test
    void addAddressNull() {
        doReturn(Optional.empty())
                .when(repository)
                .findById(TEST_ID);

        Person changePerson = service.addAddress(TEST_ID, TEST_ADDRESS);

        verify(repository).findById(TEST_ID);
        verifyNoMoreInteractions(repository);

        assertNull(changePerson);
    }
}