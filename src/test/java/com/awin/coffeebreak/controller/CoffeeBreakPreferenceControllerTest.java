package com.awin.coffeebreak.controller;

import com.awin.coffeebreak.exceptions.EmployeeNotFoundException;
import com.awin.coffeebreak.services.EmployeeRepositoryService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CoffeeBreakPreferenceController.class)
class CoffeeBreakPreferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepositoryService employeeRepositoryService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        mockMvc.perform(get("/employees/42")
                .accept("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void whenNotFound_thenReturns404() throws Exception {
        Mockito.when(employeeRepositoryService.getEmployeeById(any())).thenThrow(new EmployeeNotFoundException());
        mockMvc.perform(get("/employees/42")
                .accept("application/json"))
                .andExpect(status().isNotFound());
    }

}