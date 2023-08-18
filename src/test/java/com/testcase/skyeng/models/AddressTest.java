package com.testcase.skyeng.models;

import org.junit.jupiter.api.Test;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void creationTest() {
        Address newItem = new Address();
        newItem.setId(TEST_ID);
        newItem.setCountry(TEST_NAME);
        newItem.setCity(TEST_NAME);
        newItem.setAddressLine1(TEST_ADDRESS_LINE);
        newItem.setAddressLine2(TEST_ADDRESS_LINE);
        newItem.setIndex(TEST_INDEX);

        assertEquals(TEST_ID, newItem.getId());
        assertEquals(TEST_NAME, newItem.getCountry());
        assertEquals(TEST_NAME, newItem.getCity());
        assertEquals(TEST_ADDRESS_LINE, newItem.getAddressLine1());
        assertEquals(TEST_ADDRESS_LINE, newItem.getAddressLine2());
        assertEquals(TEST_INDEX, newItem.getIndex());
    }

    @Test
    void copyTest(){
        Address newItem = new Address();

        newItem.copy(TEST_ADDRESS);

        assertNotEquals(TEST_ID, newItem.getId());
        assertEquals(TEST_NAME, newItem.getCountry());
        assertEquals(TEST_NAME, newItem.getCity());
        assertEquals(TEST_ADDRESS_LINE, newItem.getAddressLine1());
        assertEquals(TEST_ADDRESS_LINE, newItem.getAddressLine2());
        assertEquals(TEST_INDEX, newItem.getIndex());
    }
}