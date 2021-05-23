package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class CoffeeBreakPreference {


    public enum Drink {
        COFFEE,
        TEA
    }

    public enum Food {
        SANDWICH,
        CRISPS,
        TOAST
    }

    @Id
    private LocalDate date;
    private Food food;
    private Drink drink;

    public CoffeeBreakPreference(Food food, Drink drink, LocalDate date) {
        this.food = food;
        this.drink = drink;
        this.date = date;
    }

    Food getFood() {
        return food;
    }

    Drink getDrink() {
        return drink;
    }

    LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     *  Allows the construction of an CoffeeBreakPreference object. Mandatory fields are date.
     */
    static class Builder {

        private LocalDate date;
        private Food food;
        private Drink drink;

        static Builder start() {
            return new Builder();
        }

        Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        Builder withFood(Food food) {
            this.food = food;
            return this;
        }

        Builder withDrink(Drink drink) {
            this.drink = drink;
            return this;
        }

        CoffeeBreakPreference build() {

            if (date == null || (food == null && drink == null)) {
                throw new InvalidCoffeeBreakPreferenceException();
            }

            return new CoffeeBreakPreference(food, drink, date);
        }

    }
}
