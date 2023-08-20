package com.testcase.skyeng.controllers.modelControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.models.Track;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.List;

import static com.testcase.skyeng.models.TestCredentials.TEST_POSTOFFICE;
import static com.testcase.skyeng.models.additions.PackageType.MAIL;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("admin")
class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper om;

    @BeforeEach
    void setUp(){
        om = new ObjectMapper();
    }

    @Test
    void newItem() throws Exception {
        Track newTrack = new Track();

        mockMvc.perform(post("/tracks")
                        .content(om.writeValueAsString(newTrack))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.currentState", is(0)))
                .andExpect(jsonPath("$.arrived", is(false)));
    }

    @Test
    void addPackage() throws Exception{
        mockMvc.perform(post("/tracks/1/addPackage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mailPackage", notNullValue()))
                .andExpect(jsonPath("$.mailPackage.id", is(1)));
    }

    @Test
    void addPackageWrong() throws Exception{
        mockMvc.perform(post("/tracks/1111/addPackage/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void addStartOffice() throws Exception{
        mockMvc.perform(post("/tracks/1/addStart/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path", hasSize(1)))
                .andExpect(jsonPath("$.path[0].id", is(1)));
    }

    @Test
    void addStartOfficeWrong() throws Exception{
        mockMvc.perform(post("/tracks/1111/addStart/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void addOfficeToTrack() throws Exception {
        mockMvc.perform(put("/tracks/path/1")
                        .param("officeFrom", "1")
                        .param("officeToAdd", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path", hasSize(1)));
    }

    @Test
    void nextStep() throws Exception {
        mockMvc.perform(post("/tracks/1/addStart/1"));

        mockMvc.perform(put("/tracks/path/1")
                .param("officeFrom", "1")
                .param("officeToAdd", "2"));

        mockMvc.perform(post("/tracks/path/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path", hasSize(2)))
                .andExpect(jsonPath("$.currentState", is(1)));
    }

    @Test
    void checkCurrentOffice() throws Exception {
        mockMvc.perform(post("/tracks/1/addStart/1"));

        mockMvc.perform(get("/tracks/path/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("main VAO")));
    }
}