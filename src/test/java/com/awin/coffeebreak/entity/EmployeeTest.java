package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidEmployeeException;
import com.awin.coffeebreak.testUtils.TestEntityUtility;
import com.awin.coffeebreak.testUtils.TestConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.awin.coffeebreak.testUtils.TestEntityUtility.*;

class EmployeeTest {

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
        Employee employee;
        try {
            employee = Employee.Builder.start().withId(ID).withName(NAME).build();
        } catch (InvalidEmployeeException e) {
            throw new TestConfigurationException();
        }
        assert(employee.getName()).equals(NAME);
        assert(employee.getId()).equals(ID);
    }

    @Test
    void employeeBuilderShouldReturnEmployeeWithInputFieldsSetCorrectly() {

        Employee employee = TestEntityUtility.getTestEmployee();
        assert(employee.getName()).equals(NAME);
        assert(employee.getId()).equals(ID);

        assert(employee.getContactDetails().getEmailAddress()).orElseThrow(TestConfigurationException::new).equals(EMAIL);
        assert(employee.getContactDetails().getSlackId()).orElseThrow(TestConfigurationException::new).equals(SLACK);

        employee.getPreference().values().forEach(val -> {
            assert(val.getFood().equals(FOOD));
            assert(val.getDrink().equals(DRINK));
        });
    }
}