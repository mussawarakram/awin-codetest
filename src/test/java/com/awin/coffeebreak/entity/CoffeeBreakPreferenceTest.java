package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeBreakPreferenceTest {


    private final CoffeeBreakPreference.Food FOOD = CoffeeBreakPreference.Food.SANDWICH;
    private final CoffeeBreakPreference.Drink DRINK =CoffeeBreakPreference.Drink.COFFEE;
    private final LocalDate LOCAL_DATE = LocalDate.now();

    @Test
    void ensureMissingDateThrowsException() {
        Assertions.assertThrows(InvalidCoffeeBreakPreferenceException.class, () -> CoffeeBreakPreference.Builder.start().withFood(FOOD).withDrink(DRINK).build());
    }

    @Test
    void ensureMissingFoodAndDrinkThrowsException() {
        Assertions.assertThrows(InvalidCoffeeBreakPreferenceException.class, () -> CoffeeBreakPreference.Builder.start().withDate(LOCAL_DATE).build());
    }

    @Test
    void preferenceBuilderShouldReturnPreferenceWithMandatoryFields() throws InvalidCoffeeBreakPreferenceException {
        CoffeeBreakPreference preference = CoffeeBreakPreference.Builder.start().withFood(FOOD).withDrink(DRINK).withDate(LOCAL_DATE).build();

        assert(preference.getDrink() == DRINK);
        assert(preference.getFood() == FOOD);
        assert(preference.getDate() == LOCAL_DATE);

    }
}