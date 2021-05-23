package com.awin.coffeebreak.testUtils;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.entity.ContactDetails;
import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;
import com.awin.coffeebreak.exceptions.InvalidEmployeeException;

import java.time.LocalDate;

public class EmployeeUtility {

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


}
