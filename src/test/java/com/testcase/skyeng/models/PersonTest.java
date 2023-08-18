package com.testcase.skyeng.models;

import org.junit.jupiter.api.Test;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PersonTest {
    @Test
    void creationTest() {
        Person newItem = new Person();
        newItem.setId(TEST_ID);
        newItem.setPassportNumber(TEST_PASSPORT);
        newItem.setFirstName(TEST_NAME);
        newItem.setSecondName(TEST_NAME);
        newItem.setAddress(TEST_ADDRESS);

        assertEquals(TEST_ID, newItem.getId());
        assertEquals(TEST_PASSPORT, newItem.getPassportNumber());
        assertEquals(TEST_NAME, newItem.getFirstName());
        assertEquals(TEST_NAME, newItem.getSecondName());
        assertEquals(TEST_ADDRESS, newItem.getAddress());
    }

    @Test
    void copyTest() {
        Person newItem = new Person();
        newItem.copy(TEST_PERSON);

        assertNotEquals(TEST_ID, newItem.getId());
        assertEquals(TEST_PASSPORT, newItem.getPassportNumber());
        assertEquals(TEST_NAME, newItem.getFirstName());
        assertEquals(TEST_NAME, newItem.getSecondName());
        assertEquals(TEST_ADDRESS, newItem.getAddress());
    }
}