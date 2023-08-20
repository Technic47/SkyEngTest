package com.testcase.skyeng.controllers.modelControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.PostOffice;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("admin")
class PostOfficeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void newItem() throws Exception {
        ObjectMapper om = new ObjectMapper();

        Address newAddress = new Address();
        newAddress.setCountry("Russia");
        newAddress.setCity("Tomsk");
        newAddress.setIndex(111222);
        newAddress.setAddressLine1("bls bls bls");

        PostOffice newOffice = new PostOffice();
        newOffice.setName("NewOffice");
        newOffice.setAddress(newAddress);

        mockMvc.perform(post("/postoffices")
                        .content(om.writeValueAsString(newOffice))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", notNullValue()))
                .andExpect(jsonPath("$.address.id", is(5)));
    }
}