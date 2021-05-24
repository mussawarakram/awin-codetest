package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.entity.NotificationRequest;
import com.awin.coffeebreak.exceptions.NotificationException;
import com.awin.coffeebreak.exceptions.PreferenceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private EmployeeRepositoryService employeeRepositoryService;
    private NotifierEmail notifierEmail;
    private NotifierSlack notifierSlack;

    NotificationService(EmployeeRepositoryService employeeRepositoryService, NotifierEmail notifierEmail, NotifierSlack notifierSlack) {
        this.employeeRepositoryService = employeeRepositoryService;
        this.notifierEmail = notifierEmail;
        this.notifierSlack = notifierSlack;
    }

    /**
     * This method will find the employee ID, in the repository, check the have the requested contact ID, and check they have a preference for the given day.
     *
     * @param id the Employee ID who is to be notified.
     * @param notificationRequest the notificationRequest object containing contact method and date.
     * @return the success or failure of the notification hand-off
     */
    public boolean sendNotification(Integer id, NotificationRequest notificationRequest) {

        Employee employee = employeeRepositoryService.getEmployeeById(id);
        CoffeeBreakPreference preference = employee.getPreference().stream().filter(pref -> pref.getDate() == notificationRequest.getDate()).findFirst().orElseThrow(PreferenceNotFoundException::new);

        if (notificationRequest.getNotificationType().equals(NotificationRequest.NotificationType.SLACK)) {
            String userId = employee.getContactDetails().getSlackId().orElseThrow(NotificationException::new);
            notifierSlack.sendNotification(userId, preference);
            return true;
        } else if (notificationRequest.getNotificationType().equals(NotificationRequest.NotificationType.EMAIL)) {
            String userId = employee.getContactDetails().getSlackId().orElseThrow(NotificationException::new);
            notifierEmail.sendNotification(userId, preference);
            return true;
        }

        return false;
    }

}
