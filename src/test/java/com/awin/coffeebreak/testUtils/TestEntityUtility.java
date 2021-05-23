package com.awin.coffeebreak.testUtils;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.entity.ContactDetails;
import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.entity.NotificationRequest;
import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;
import com.awin.coffeebreak.exceptions.InvalidEmployeeException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestEntityUtility {

    public static final String NAME = "Test";
    public static final Integer ID = 1;
    public static final String EMAIL = "test@email.com";
    public static final String SLACK = "slackId";
    public static final CoffeeBreakPreference.Food FOOD = CoffeeBreakPreference.Food.SANDWICH;
    public static final CoffeeBreakPreference.Drink DRINK = CoffeeBreakPreference.Drink.COFFEE;
    public static final LocalDate LOCALDATE = LocalDate.now();

    public static Employee getTestEmployee() {
        ContactDetails contactDetails = ContactDetails.Builder.start().withEmailAddress(EMAIL).withSlackId(SLACK).build();
        CoffeeBreakPreference preference;
        try {
            preference = CoffeeBreakPreference.Builder.start().withFood(FOOD).withDrink(DRINK).withDate(LOCALDATE).build();
        } catch (InvalidCoffeeBreakPreferenceException e) {
            throw new TestConfigurationException();
        }
        try {
            return Employee.Builder.start().withId(ID).withName(NAME).withContactDetails(contactDetails).withPreference(preference).build();
        } catch (InvalidEmployeeException e) {
            throw new TestConfigurationException();
        }
    }

    public static Employee getTestEmployeeWithoutContactDetails() {
        Employee employee = getTestEmployee();
        employee.setContactDetails(ContactDetails.Builder.start().build());
        return employee;
    }

    public static NotificationRequest getNotificationTestForEmail() {
        return new NotificationRequest(NotificationRequest.NotificationType.EMAIL, TestEntityUtility.LOCALDATE);
    }

    public static NotificationRequest getNotificationTestForSlack() {
        return new NotificationRequest(NotificationRequest.NotificationType.SLACK, TestEntityUtility.LOCALDATE);
    }

    public static NotificationRequest getNotificationTestForRandomDate() {
        int hundredYears = 100 * 365;
        LocalDate randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears));
        return new NotificationRequest(NotificationRequest.NotificationType.SLACK, randomDate);
    }

}
