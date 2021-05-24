package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidCoffeeBreakPreferenceException;

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

    private Food food;
    private Drink drink;

    public CoffeeBreakPreference(Food food, Drink drink) {
        this.food = food;
        this.drink = drink;
    }

    public Food getFood() {
        return food;
    }

    public Drink getDrink() {
        return drink;
    }

    /**
     * @return a HTML formatted string representation of the object
     */
    String toHtmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        sb.append("<li>").append("Food: ").append(food.toString()).append("</li>");
        sb.append("<li>").append("Drink: ").append(drink.toString()).append("</li>");
        sb.append("</ul>");
        return sb.toString();
    }

    /**
     *  Allows the construction of an CoffeeBreakPreference object. Mandatory fields are date.
     */
    public static class Builder {

        private Food food;
        private Drink drink;

        public static Builder start() {
            return new Builder();
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

            if (food == null && drink == null) {
                throw new InvalidCoffeeBreakPreferenceException();
            }

            return new CoffeeBreakPreference(food, drink);
        }

    }
}
