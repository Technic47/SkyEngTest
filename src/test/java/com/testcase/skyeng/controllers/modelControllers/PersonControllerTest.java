package com.testcase.skyeng.controllers.modelControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("admin")
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addAddressById() throws Exception {
        mockMvc.perform(post("/persons/1/addAddress/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", notNullValue()))
                .andExpect(jsonPath("$.address.id", is(1)));
    }

    @Test
    void addAddressByIdWrong() throws Exception {
        mockMvc.perform(post("/persons/1/addAddress/1111"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void newItem() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Person newPerson = new Person();
        newPerson.setFirstName("bla bla");
        newPerson.setSecondName("pla pla");
        newPerson.setPassportNumber(654321L);

        Address newAddress = new Address();
        newAddress.setCountry("Russia");
        newAddress.setCity("Tomsk");
        newAddress.setIndex(111222);
        newAddress.setAddressLine1("bls bls bls");

        newPerson.setAddress(newAddress);

        mockMvc.perform(post("/persons")
                        .content(om.writeValueAsString(newPerson))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", notNullValue()))
                .andExpect(jsonPath("$.address.id", is(5)));
    }
}