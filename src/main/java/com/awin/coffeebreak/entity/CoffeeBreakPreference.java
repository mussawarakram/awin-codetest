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

    public Food getFood() {
        return food;
    }

    public Drink getDrink() {
        return drink;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    String toHtmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        sb.append("<li>").append("Date: ").append(date.toString()).append("</li>");
        sb.append("<li>").append("Food: ").append(food.toString()).append("</li>");
        sb.append("<li>").append("Drink: ").append(drink.toString()).append("</li>");
        sb.append("</ul>");
        return sb.toString();
    }

    /**
     *  Allows the construction of an CoffeeBreakPreference object. Mandatory fields are date.
     */
    public static class Builder {

        private LocalDate date;
        private Food food;
        private Drink drink;

        public static Builder start() {
            return new Builder();
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withFood(Food food) {
            this.food = food;
            return this;
        }

        public Builder withDrink(Drink drink) {
            this.drink = drink;
            return this;
        }

        public CoffeeBreakPreference build() throws InvalidCoffeeBreakPreferenceException {

            if (date == null || (food == null && drink == null)) {
                throw new InvalidCoffeeBreakPreferenceException();
            }

            return new CoffeeBreakPreference(food, drink, date);
        }

    }
}
