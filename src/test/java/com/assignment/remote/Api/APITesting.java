package com.assignment.remote.Api;


import com.assignment.remote.BaseTestService;
import com.assignment.remote.dto.BaseRequestBody;
import com.assignment.remote.dto.ChildDto;
import com.assignment.remote.dto.ParentDto;
import com.assignment.remote.utils.UrlConstraint;
import com.assignment.remote.utils.Util;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
public class APITesting extends BaseTestService {
    @Autowired
    MockMvc mockMvc;
    BaseRequestBody<Object> request = null;
    @Qualifier("prettyGson")
    @Autowired
    private Gson gson;

    @BeforeEach
    void prepareBaseRequestBody() {
        request = new BaseRequestBody<>();
        request.setChannelId("Assignment");
        request.setRequestId(String.valueOf(System.nanoTime()));
        request.setRequestTimeStamp(Util.dateToString(new Date(), "yyyy-MM-dd"));
    }

    @Test
    @DisplayName("Test Creation API")
    void testFamilyCreationAPI() throws Exception {
        request.setData(ParentDto.builder()
                .firstName("Delower")
                .lastName("Hossain")
                .street("Tejgaon industrial area")
                .state("state name")
                .city("Dhaka")
                .zip("zip code")
                .children(Collections.singletonList(ChildDto.builder()
                        .firstName("Golam")
                        .lastName("Kibria")
                        .build()))
                .build());

        mockMvc.perform(post(UrlConstraint.ROOT + UrlConstraint.FamilyApiManagement.create)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode").value(String.valueOf(HttpStatus.OK.value())))
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    @DisplayName("Test Update API")
    void testFamilyUpdateAPI() throws Exception {
        request.setData(ParentDto.builder()
                .firstName("MD: Delower")
                .lastName("Hossain")
                .street("Tejgaon industrial area")
                .state("state name")
                .city("Dhaka")
                .zip("zip code")
                .children(Collections.singletonList(ChildDto.builder()
                        .firstName("MD: Golam")
                        .lastName("Kibria")
                        .build()))
                .build());

        mockMvc.perform(put(UrlConstraint.ROOT + "/update/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode").value(String.valueOf(HttpStatus.OK.value())))
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    @DisplayName("Test Delete API")
    void testFamilyDeleteAPI() throws Exception {
        mockMvc.perform(delete(UrlConstraint.ROOT + "/delete/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode").value(String.valueOf(HttpStatus.OK.value())))
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

}
