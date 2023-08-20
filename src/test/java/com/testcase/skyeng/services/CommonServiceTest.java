package com.testcase.skyeng.services;

import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.repositories.AddressRepository;
import com.testcase.skyeng.services.modelServices.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.testcase.skyeng.models.TestCredentials.TEST_ADDRESS;
import static com.testcase.skyeng.models.TestCredentials.TEST_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class CommonServiceTest {
    private AddressService service;
    @MockBean
    private AddressRepository repository;

    @BeforeEach
    void setUp() {
        service = new AddressService(repository);
    }

    @Test
    void indexTest() {
        List<Address> list = new ArrayList<>();
        list.add(TEST_ADDRESS);
        doReturn(list)
                .when(repository)
                .findAll();

        List<Address> addressList = service.index();

        verify(repository).findAll();

        assertNotNull(addressList);
        assertEquals(1, addressList.size());
    }

    @Test
    void getByIdOrNullTest() {
        doReturn(Optional.of(TEST_ADDRESS))
                .when(repository)
                .findById(TEST_ID);

        Address address = service.getById(TEST_ID);

        verify(repository).findById(TEST_ID);

        assertNotNull(address);
    }

    @Test
    void getByIdTestNull() {
        doReturn(Optional.empty())
                .when(repository)
                .findById(TEST_ID);

        assertThrows(ResourceNotFoundException.class, () -> service.getById(TEST_ID));
    }

    @Test
    void updateItemTest() {
        Address address = new Address();
        doReturn(Optional.of(address))
                .when(repository)
                .findById(1L);

        address.copy(TEST_ADDRESS);

        doReturn(TEST_ADDRESS)
                .when(repository)
                .save(address);


        Address updateItem = service.updateItem(1L, TEST_ADDRESS);

        verify(repository).findById(1L);
        verify(repository).save(address);
        verify(repository, times(0)).save(TEST_ADDRESS);

        assertEquals(updateItem, TEST_ADDRESS);
    }

    @Test
    void updateItemTestNull() {
        doReturn(Optional.empty())
                .when(repository)
                .findById(1L);

        doReturn(TEST_ADDRESS)
                .when(repository)
                .save(TEST_ADDRESS);

        Address updateItem = service.updateItem(1L, TEST_ADDRESS);

        verify(repository).findById(1L);
        verify(repository).save(TEST_ADDRESS);

        assertEquals(updateItem, TEST_ADDRESS);
    }

    @Test
    void delById() {
        doReturn(Optional.of(TEST_ADDRESS))
                .when(repository)
                .findById(1L);

        assertTrue(service.delById(1L));
        assertThrows(ResourceNotFoundException.class, () -> service.delById(TEST_ID));
    }
}