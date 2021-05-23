package com.awin.coffeebreak.controller;

import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.services.EmployeeRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeeBreakPreferenceController {

    private EmployeeRepositoryService employeeRepositoryService;

    private CoffeeBreakPreferenceController(EmployeeRepositoryService employeeRepositoryService) {
        this.employeeRepositoryService = employeeRepositoryService;
    }

    //TODO: implement text/html, application/json and text/xml response formats
    /**
     * Retrieves the current preferences for an employee
     */
    @GetMapping(path = "/employees/{id}", produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<?> getEmployee(@RequestHeader String accept, @PathVariable Integer id) {
        Employee employee = employeeRepositoryService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
