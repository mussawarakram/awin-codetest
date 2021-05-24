package com.awin.coffeebreak.controller;

import com.awin.coffeebreak.exceptions.EmployeeNotFoundException;
import com.awin.coffeebreak.exceptions.NotificationException;
import com.awin.coffeebreak.exceptions.PreferenceNotFoundException;
import com.awin.coffeebreak.services.EmployeeRepositoryService;
import com.awin.coffeebreak.services.NotificationService;
import com.awin.coffeebreak.testUtils.TestEntityUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CoffeeBreakPreferenceController.class)
class CoffeeBreakPreferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepositoryService employeeRepositoryService;

    @MockBean
    private NotificationService notificationService;

    @Test
    void whenValidInputForEmployee_thenReturns200Json() throws Exception {
        mockMvc.perform(get("/employees/42")
                .accept("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidInputForEmployee_thenReturns200Html() throws Exception {
        Mockito.when(employeeRepositoryService.getEmployeeById(any())).thenReturn(TestEntityUtility.getTestEmployee());
        mockMvc.perform(get("/employees/42")
                .accept("text/html"))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidInputForEmployee_thenReturns200Xml() throws Exception {
        mockMvc.perform(get("/employees/42")
                .accept("text/xml"))
                .andExpect(status().isOk());
    }

    @Test
    void whenNotFoundForEmployee_thenReturns404() throws Exception {
        Mockito.when(employeeRepositoryService.getEmployeeById(any())).thenThrow(new EmployeeNotFoundException());
        mockMvc.perform(get("/employees/42")
                .accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenValidInputForNotification_thenReturns200() throws Exception {
        Mockito.when(notificationService.sendNotification(any(), any())).thenReturn(true);
        mockMvc.perform(post("/employees/42/sendNotification")
                .content(getJsonNotificationRequestBody())
                .contentType("application/json"))
                .andExpect(status().isAccepted());
    }

    @Test
    void whenInvalidContactDetails_thenReturns400() throws Exception {
        Mockito.when(notificationService.sendNotification(any(), any())).thenThrow(new NotificationException());
        mockMvc.perform(post("/employees/42/sendNotification")
                .content(getJsonNotificationRequestBody())
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidPreferences_thenReturns400() throws Exception {
        Mockito.when(notificationService.sendNotification(any(), any())).thenThrow(new PreferenceNotFoundException());
        mockMvc.perform(post("/employees/42/sendNotification")
                .content(getJsonNotificationRequestBody())
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    // bug with deserialization of Dates necessitates the use of this string
    private static String getJsonNotificationRequestBody() {
        return "{\"notificationType\":\"EMAIL\",\"date\":\"2021-05-24\"}";
    }
}