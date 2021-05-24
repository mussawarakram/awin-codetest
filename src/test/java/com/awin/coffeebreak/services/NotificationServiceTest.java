package com.awin.coffeebreak.services;

import com.awin.coffeebreak.exceptions.NotificationException;
import com.awin.coffeebreak.exceptions.PreferenceNotFoundException;
import com.awin.coffeebreak.testUtils.TestEntityUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {

    @MockBean
    private EmployeeRepositoryService employeeRepositoryService;

    @MockBean
    private NotifierEmail notifierEmail;

    @MockBean
    private NotifierSlack notifierSlack;


    private NotificationService notificationService;

    @BeforeAll
    void setup() {
        notificationService = new NotificationService(employeeRepositoryService, notifierEmail, notifierSlack);
    }

    @Test
    void sendNotificationWithValidContactDetailsDoesNotThrowException() {
        Mockito.when(employeeRepositoryService.getEmployeeById(TestEntityUtility.ID)).thenReturn(TestEntityUtility.getTestEmployee());
        notificationService.sendNotification(TestEntityUtility.ID, TestEntityUtility.getNotificationTestForEmail());
        notificationService.sendNotification(TestEntityUtility.ID, TestEntityUtility.getNotificationTestForSlack());
    }

    @Test
    void sendNotificationWithMissingContactDetailsDoesThrowException() throws NotificationException {
        Mockito.when(employeeRepositoryService.getEmployeeById(TestEntityUtility.ID)).thenReturn(TestEntityUtility.getTestEmployeeWithoutContactDetails());
        Assertions.assertThrows( NotificationException.class, () -> notificationService.sendNotification(TestEntityUtility.ID, TestEntityUtility.getNotificationTestForEmail()));
        Assertions.assertThrows( NotificationException.class, () -> notificationService.sendNotification(TestEntityUtility.ID, TestEntityUtility.getNotificationTestForSlack()));
    }

    @Test
    void sendNotificationWithInvalidPreferenceDateDoesThrowException() throws PreferenceNotFoundException {
        Mockito.when(employeeRepositoryService.getEmployeeById(TestEntityUtility.ID)).thenReturn(TestEntityUtility.getTestEmployee());
        Assertions.assertThrows( PreferenceNotFoundException.class, () -> notificationService.sendNotification(TestEntityUtility.ID, TestEntityUtility.getNotificationTestForRandomDate()));
    }
}