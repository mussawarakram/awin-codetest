package com.awin.coffeebreak.config;
import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import com.awin.coffeebreak.entity.ContactDetails;
import com.awin.coffeebreak.entity.Employee;
import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;
import com.awin.coffeebreak.exceptions.InvalidEmployeeException;
import com.awin.coffeebreak.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 *  This class is only to populate some mock data for playing around in the application.
 */
@Service
public class ApplicationSetup {

    private static EmployeeRepository employeeRepository;

    public ApplicationSetup(EmployeeRepository employeeRepository) {
        ApplicationSetup.employeeRepository = employeeRepository;
    }

    public static void initData() {
        Employee employee;
        Employee employee2;
        Employee employee3;


        try {
            employee = Employee.Builder.start()
                    .withName("John")
                    .withId(10)
                    .withContactDetails(ContactDetails.Builder.start().withEmailAddress("john@email.com").withSlackId("John@Slack").build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.SANDWICH).withDrink(CoffeeBreakPreference.Drink.COFFEE).withDate(LocalDate.now()).build())
                    .build();

            employee2 = Employee.Builder.start()
                    .withName("Emma")
                    .withId(3)
                    .withContactDetails(ContactDetails.Builder.start().withSlackId("Emma@Slack").build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.CRISPS).withDrink(CoffeeBreakPreference.Drink.TEA).withDate(LocalDate.now()).build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.SANDWICH).withDrink(CoffeeBreakPreference.Drink.TEA).withDate(LocalDate.parse("2021-05-24")).build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.TOAST).withDrink(CoffeeBreakPreference.Drink.COFFEE).withDate(LocalDate.parse("2021-05-25")).build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.CRISPS).withDrink(CoffeeBreakPreference.Drink.COFFEE).withDate(LocalDate.parse("2021-05-26")).build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.SANDWICH).withDrink(CoffeeBreakPreference.Drink.COFFEE).withDate(LocalDate.parse("2021-05-27")).build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.TOAST).withDrink(CoffeeBreakPreference.Drink.TEA).withDate(LocalDate.parse("2021-05-28")).build())
                    .build();

            employee3 = Employee.Builder.start()
                    .withName("Jack")
                    .withId(224)
                    .withContactDetails(ContactDetails.Builder.start().withEmailAddress("Jack@email.com").build())
                    .withPreference(CoffeeBreakPreference.Builder.start().withFood(CoffeeBreakPreference.Food.TOAST).withDrink(CoffeeBreakPreference.Drink.TEA).withDate(LocalDate.now()).build())
                    .build();
        } catch (InvalidCoffeeBreakPreferenceException | InvalidEmployeeException e) {
            throw new RuntimeException();
        }

        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
    }
}