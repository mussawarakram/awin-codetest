package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidEmployeeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class EmployeeTest {

    private final String NAME = "Test";
    private final Integer ID = 1;
    private final String EMAIL = "test@email.com";
    private final String SLACK = "slackId";
    private final CoffeeBreakPreference.Food FOOD = CoffeeBreakPreference.Food.SANDWICH;
    private final CoffeeBreakPreference.Drink DRINK = CoffeeBreakPreference.Drink.COFFEE;
    private final LocalDate LOCALDATE = LocalDate.now();

    @Test
    void ensureMissingIdThrowsException() {
        Assertions.assertThrows(InvalidEmployeeException.class, () -> Employee.Builder.start().withName(NAME).build());
    }

    @Test
    void ensureMissingNameThrowsException() {
        Assertions.assertThrows(InvalidEmployeeException.class, () -> Employee.Builder.start().withId(ID).build());
    }

    @Test
    void employeeBuilderShouldReturnEmployeeWithMandatoryFields() {
        Employee employee = Employee.Builder.start().withId(ID).withName(NAME).build();
        assert(employee.getName()).equals(NAME);
        assert(employee.getId()).equals(ID);
    }

    @Test
    void employeeBuilderShouldReturnEmployeeWithInputFieldsSetCorrectly() {
        ContactDetails contactDetails = ContactDetails.Builder.start().withEmailAddress(EMAIL).withSlackId(SLACK).build();
        CoffeeBreakPreference preference = CoffeeBreakPreference.Builder.start().withFood(FOOD).withDrink(DRINK).withDate(LOCALDATE).build();
        Employee employee = Employee.Builder.start().withId(ID).withName(NAME).withContactDetails(contactDetails).withPreference(preference).build();
        assert(employee.getName()).equals(NAME);
        assert(employee.getId()).equals(ID);

        assert(employee.getContactDetails().getEmailAddress()).equals(EMAIL);
        assert(employee.getContactDetails().getSlackId()).equals(SLACK);

        employee.getPreference().forEach(pref -> {
            assert(pref).getDate().isEqual(LOCALDATE);
            assert(pref).getFood().equals(FOOD);
            assert(pref).getDrink().equals(DRINK);
        });
    }
}