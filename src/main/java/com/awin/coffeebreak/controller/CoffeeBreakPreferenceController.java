package com.awin.coffeebreak.controller;

import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.entity.NotificationRequest;
import com.awin.coffeebreak.services.EmployeeRepositoryService;
import com.awin.coffeebreak.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeeBreakPreferenceController {

    private EmployeeRepositoryService employeeRepositoryService;
    private NotificationService notificationService;

    private CoffeeBreakPreferenceController(EmployeeRepositoryService employeeRepositoryService, NotificationService notificationService) {
        this.employeeRepositoryService = employeeRepositoryService;
        this.notificationService = notificationService;
    }

    /**
     * This method fetches the ID'd employee object if it exists, or throws an exception if it doesn't.
     *
     * @param accept The response formats accepted by the consumer
     * @param id The Employee ID to attempt to retrieve
     * @return a response entity with the requested object, or an exception defined in the global handler
     */
    @GetMapping(path = "/employees/{id}", produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<?> getEmployee(@RequestHeader String accept, @PathVariable Integer id) {
        Employee employee = employeeRepositoryService.getEmployeeById(id);
        if (accept.equals(MediaType.TEXT_HTML_VALUE)) {
            return new ResponseEntity<>(employee.toHtmlString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }

    /**
     * This method will send a notification to the employee ID in question.
     *
     * @param id The employee ID to attempt to notify
     * @param notificationRequest a request body consisting of contact mechanism and date
     * @return A status code representing whether the request was valid or not
     */
    @PostMapping(path = "/employees/{id}/sendNotification")
    public ResponseEntity<?> sendNotification(@PathVariable Integer id, @RequestBody NotificationRequest notificationRequest) {
        boolean success = notificationService.sendNotification(id, notificationRequest);
        if (success) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
