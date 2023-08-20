package com.testcase.skyeng.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.skyeng.models.Address;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("admin")
class CommonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/addresses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void newItem() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Address newAddress = new Address();
        newAddress.setCountry("Russia");
        newAddress.setCity("Tomsk");
        newAddress.setIndex(111222);
        newAddress.setAddressLine1("bls bls bls");

        mockMvc.perform(post("/addresses")
                        .content(om.writeValueAsString(newAddress))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.country", is("Russia")))
                .andExpect(jsonPath("$.city", is("Tomsk")))
                .andExpect(jsonPath("$.index", is(111222)))
                .andExpect(jsonPath("$.addressLine1", is("bls bls bls")));

        mockMvc.perform(get("/addresses"))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void newItemWrong() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Address newAddress = new Address();

        mockMvc.perform(post("/addresses")
                        .content(om.writeValueAsString(newAddress))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/addresses/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.country", is("Russia")))
                .andExpect(jsonPath("$.city", is("Moscow")))
                .andExpect(jsonPath("$.index", is(152055)))
                .andExpect(jsonPath("$.addressLine1", is("street1 st.")));
    }

    @Test
    void getByIdWrong() throws Exception {
        mockMvc.perform(get("/addresses/1111"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateItem() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Address newAddress = new Address();
        newAddress.setCountry("Russia");
        newAddress.setCity("Tomsk");
        newAddress.setIndex(111222);
        newAddress.setAddressLine1("bls bls bls");

        mockMvc.perform(put("/addresses/1")
                        .content(om.writeValueAsString(newAddress))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.country", is("Russia")))
                .andExpect(jsonPath("$.city", is("Tomsk")))
                .andExpect(jsonPath("$.index", is(111222)))
                .andExpect(jsonPath("$.addressLine1", is("bls bls bls")));

        mockMvc.perform(get("/addresses"))
                .andExpect(jsonPath("[0].country", is("Russia")));
    }

    /**
     * TODO
     * Can`t delete if item is used.
     * @throws Exception
     */

//    @Test
//    void delById() throws Exception {
//        mockMvc.perform(delete("/addresses/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//    }

    @Test
    void delByIdFalse() throws Exception {
        mockMvc.perform(delete("/addresses/1111"))
                .andExpect(status().is4xxClientError());
    }
}