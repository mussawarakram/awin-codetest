package com.awin.coffeebreak.services;

import com.awin.coffeebreak.exceptions.EmployeeNotFoundException;
import com.awin.coffeebreak.repositories.EmployeeRepository;
import com.awin.coffeebreak.testUtils.TestEntityUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryServiceTest {

    @MockBean
    EmployeeRepository employeeRepository;

    private EmployeeRepositoryService employeeRepositoryService;

    @BeforeAll
    void setup() {
        employeeRepositoryService = new EmployeeRepositoryService(employeeRepository);
    }

    @Test
    void getEmployeeByIdWhenFoundDoesNotThrowException() throws EmployeeNotFoundException {
        Mockito.when(employeeRepository.findById(TestEntityUtility.ID)).thenReturn(Optional.of(TestEntityUtility.getTestEmployee()));
        employeeRepositoryService.getEmployeeById(TestEntityUtility.ID);
    }

    @Test
    void getEmployeeByIdWhenNotFoundDoesThrowException() throws EmployeeNotFoundException {
        Assertions.assertThrows( EmployeeNotFoundException.class, () -> employeeRepositoryService.getEmployeeById(TestEntityUtility.ID));
    }
}