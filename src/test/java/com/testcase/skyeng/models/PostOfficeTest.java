package com.testcase.skyeng.models;

import org.junit.jupiter.api.Test;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.*;

class PostOfficeTest {
    @Test
    void creationTest(){
        PostOffice newItem = new PostOffice();
        newItem.setId(TEST_ID);
        newItem.setName(TEST_NAME);
        newItem.setAddress(TEST_ADDRESS);

        assertEquals(TEST_ID, newItem.getId());
        assertEquals(TEST_NAME, newItem.getName());
        assertEquals(TEST_ADDRESS, newItem.getAddress());
        assertEquals(TEST_INDEX, newItem.getIndex());
    }

    @Test
    void copyTest(){
        PostOffice newItem = new PostOffice();
        newItem.copy(TEST_POSTOFFICE);

        assertNotEquals(TEST_ID, newItem.getId());
        assertEquals(TEST_NAME, newItem.getName());
        assertEquals(TEST_ADDRESS, newItem.getAddress());
        assertEquals(TEST_INDEX, newItem.getIndex());
    }
}