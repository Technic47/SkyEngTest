package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.repositories.AddressRepository;
import org.assertj.core.condition.AnyOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.testcase.skyeng.models.TestCredentials.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressServiceTest {
    private AddressService service;
    @MockBean
    private AddressRepository repository;

    @BeforeEach
    void setUp() {
        service = new AddressService(repository);
    }


    @Test
    void checkExistTrue() {
        doReturn(Optional.of(TEST_ADDRESS))
                .when(repository)
                .findByCountryAndCityAndAddressLine1AndAddressLine2(TEST_NAME, TEST_NAME, TEST_ADDRESS_LINE, TEST_ADDRESS_LINE);

        service.checkExist(TEST_ADDRESS);

        verify(repository).findByCountryAndCityAndAddressLine1AndAddressLine2(TEST_NAME, TEST_NAME, TEST_ADDRESS_LINE, TEST_ADDRESS_LINE);
        verify(repository, times(0)).save(TEST_ADDRESS);
    }

    @Test
    void checkExistFalce() {
        doReturn(Optional.empty())
                .when(repository)
                .findByCountryAndCityAndAddressLine1AndAddressLine2(TEST_NAME, TEST_NAME, TEST_ADDRESS_LINE, TEST_ADDRESS_LINE);

        service.checkExist(TEST_ADDRESS);

        verify(repository).findByCountryAndCityAndAddressLine1AndAddressLine2(TEST_NAME, TEST_NAME, TEST_ADDRESS_LINE, TEST_ADDRESS_LINE);
        verify(repository, times(1)).save(TEST_ADDRESS);
    }
}