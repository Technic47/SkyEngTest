package com.testcase.skyeng.controllers.modelControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.MailPackage;
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

import static com.testcase.skyeng.models.additions.PackageType.MAIL;
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
class MailPackageControllerTest {
    @Autowired
    private MockMvc mockMvc;

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

        MailPackage newPackage = new MailPackage();
        newPackage.setType(MAIL);
        newPackage.setReceiver(newPerson);
        newPackage.setReceiverAddress(newAddress);

        mockMvc.perform(post("/mailpackages")
                        .content(om.writeValueAsString(newPackage))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiverAddress", notNullValue()))
                .andExpect(jsonPath("$.receiverAddress.id", is(5)))
                .andExpect(jsonPath("$.receiver", notNullValue()))
                .andExpect(jsonPath("$.receiver.id", is(2)));
    }
}