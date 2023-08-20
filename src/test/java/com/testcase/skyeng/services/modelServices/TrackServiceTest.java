package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import com.testcase.skyeng.models.additions.PackageType;
import com.testcase.skyeng.repositories.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TrackServiceTest {
    private TrackService service;
    @MockBean
    private TrackRepository repository;
    @MockBean
    private PostOfficeService postOfficeService;
    private MailPackage mailPackage;

    @BeforeEach
    void setUp() {
        service = new TrackService(repository, postOfficeService);
        mailPackage = new MailPackage(TEST_ID, PackageType.MAIL, TEST_ADDRESS.getIndex(), TEST_ADDRESS, TEST_PERSON);
    }

    @Test
    void addOfficeFrom() {
        List<PostOffice> officeList = new ArrayList<>(List.of(TEST_POSTOFFICE));
        Track track = new Track(mailPackage, officeList);
        PostOffice newOne = new PostOffice();

        doReturn(Optional.of(track))
                .when(repository)
                .findById(TEST_ID);

        doReturn(track)
                .when(repository)
                .save(any(Track.class));

        Track changeTrack = service.addOfficeFrom(TEST_ID, TEST_POSTOFFICE, List.of(newOne));

        verify(repository).findById(TEST_ID);
        verify(repository).save(any(Track.class));

        assertNotNull(changeTrack);
        assertEquals(2, changeTrack.getPath().size());
    }

    @Test
    void addOfficeFrom2() {
        List<PostOffice> officeList = new ArrayList<>(List.of(TEST_POSTOFFICE));
        Track track = new Track(mailPackage, officeList);
        PostOffice newOne = new PostOffice();

        doReturn(Optional.of(track))
                .when(repository)
                .findById(TEST_ID);

        doReturn(track)
                .when(repository)
                .save(any(Track.class));

        Track changeTrack = service.addOfficeFrom(TEST_ID, TEST_POSTOFFICE, newOne);

        verify(repository).findById(TEST_ID);
        verify(repository).save(any(Track.class));

        assertNotNull(changeTrack);
        assertEquals(2, changeTrack.getPath().size());
    }

    @Test
    void isArrived() {
        List<PostOffice> officeList = new ArrayList<>(List.of(TEST_POSTOFFICE));
        Track track = new Track(mailPackage, officeList);

        doReturn(Optional.of(track))
                .when(repository)
                .findById(TEST_ID);

        assertFalse(service.isArrived(TEST_ID));
    }
}