package com.testcase.skyeng.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.*;

class TrackTest {
    private Track track;

    @BeforeEach
    void setUp() {
        List<PostOffice> addressList = new ArrayList<>();
        addressList.add(TEST_POSTOFFICE);
        track = new Track(TEST_MAILPACKAGE, addressList);
        track.setId(TEST_ID);
    }

    @Test
    void creationTest() {
        assertEquals(TEST_ID, track.getId());
        assertEquals(TEST_MAILPACKAGE, track.getMailPackage());
        assertNotNull(track.getPath());
        assertEquals(0, track.getCurrentState());
        assertFalse(track.isArrived());
    }

    @Test
    void addPostOfficeAfterTest() {
        PostOffice newOffice = new PostOffice();
        track.addPostOfficeAfter(TEST_POSTOFFICE, newOffice);

        assertEquals(2, track.getPath().size());
        assertEquals(TEST_POSTOFFICE, track.getPath().get(0));
    }

    @Test
    void addPostOfficeAfterListTest() {
        List<PostOffice> addressList = new ArrayList<>();
        PostOffice newOffice1 = new PostOffice();
        PostOffice newOffice2 = new PostOffice();
        PostOffice newOffice3 = new PostOffice();
        addressList.add(newOffice1);
        addressList.add(newOffice2);
        addressList.add(newOffice3);
        track.addPostOfficeAfter(TEST_POSTOFFICE, addressList);

        assertEquals(4, track.getPath().size());
        assertEquals(TEST_POSTOFFICE, track.getPath().get(0));
    }

    @Test
    void getCurrentOfficeTest() {
        assertEquals(TEST_POSTOFFICE, track.getCurrentOffice());
    }

    @Test
    void moveToNextStepTest() {
        PostOffice newOffice = new PostOffice();
        track.addPostOfficeAfter(TEST_POSTOFFICE, newOffice);
        track.moveToNextStep();
        assertNotEquals(TEST_POSTOFFICE, track.getCurrentOffice());
        assertEquals(1, track.getCurrentState());
    }
}