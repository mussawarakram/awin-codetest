package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoffeeBreakPreferenceTest {


    private final CoffeeBreakPreference.Food FOOD = CoffeeBreakPreference.Food.SANDWICH;
    private final CoffeeBreakPreference.Drink DRINK =CoffeeBreakPreference.Drink.COFFEE;

    @Test
    void ensureMissingFoodAndDrinkThrowsException() {
        Assertions.assertThrows(InvalidCoffeeBreakPreferenceException.class, () -> CoffeeBreakPreference.Builder.start().build());
    }

    @Test
    void preferenceBuilderShouldReturnPreferenceWithMandatoryFields() throws InvalidCoffeeBreakPreferenceException {
        CoffeeBreakPreference preference = CoffeeBreakPreference.Builder.start().withFood(FOOD).withDrink(DRINK).build();
        assert(preference.getDrink() == DRINK);
        assert(preference.getFood() == FOOD);

    }
}