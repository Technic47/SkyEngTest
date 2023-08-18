package com.testcase.skyeng.models;

import com.testcase.skyeng.models.additions.PackageType;
import org.junit.jupiter.api.Test;

import static com.testcase.skyeng.models.TestCredentials.*;
import static com.testcase.skyeng.models.additions.PackageType.MAIL;
import static org.junit.jupiter.api.Assertions.*;

class MailPackageTest {
    @Test
    void creationTest() {
        MailPackage newItem = new MailPackage();
        newItem.setId(TEST_ID);
        newItem.setType(MAIL);
        newItem.setReceiverAddress(TEST_ADDRESS);
        newItem.setReceiver(TEST_PERSON);

        assertEquals(TEST_ID, newItem.getId());
        assertEquals(MAIL, newItem.getType());
        assertEquals(TEST_ADDRESS, newItem.getReceiverAddress());
        assertEquals(TEST_INDEX, newItem.getReceiverIndex());
        assertEquals(TEST_PERSON, newItem.getReceiver());
    }

    @Test
    void copyTest() {
        MailPackage newItem = new MailPackage();
        newItem.copy(TEST_MAILPACKAGE);

        assertNotEquals(TEST_ID, newItem.getId());
        assertEquals(MAIL, newItem.getType());
        assertEquals(TEST_ADDRESS, newItem.getReceiverAddress());
        assertEquals(TEST_INDEX, newItem.getReceiverIndex());
        assertEquals(TEST_PERSON, newItem.getReceiver());
    }
}