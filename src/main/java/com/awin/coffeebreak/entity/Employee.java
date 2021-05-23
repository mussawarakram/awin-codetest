package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidEmployeeException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="employees")
public class Employee {

    @Id
    private Integer id;
    private String name;
    private List<CoffeeBreakPreference> preferences;
    private ContactDetails contactDetails;

    private Employee(Integer id, String name, List<CoffeeBreakPreference> preferences, ContactDetails contactDetails) {
        this.name = name;
        this.id = id;
        this.preferences = preferences;
        this.contactDetails = contactDetails;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    List<CoffeeBreakPreference> getPreference() {
        return preferences;
    }

    public void setPreference(List<CoffeeBreakPreference> preferences) {
        this.preferences = preferences;
    }

    ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    /**
     *  Allows the construction of an Employee object. Mandatory fields are name and id.
     */
    public static class Builder {

        private Integer id;
        private String name;
        private ContactDetails contactDetails;
        private List<CoffeeBreakPreference> preferences = new ArrayList<>();

        public static Builder start() {
            return new Builder();
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPreference(CoffeeBreakPreference preference) {
            preferences.add(preference);
            return this;
        }

        public Builder withContactDetails(ContactDetails contactDetails) {
            this.contactDetails = contactDetails;
            return this;
        }

        public Employee build() throws InvalidEmployeeException {

            if (id == null || name == null) {
                throw new InvalidEmployeeException();
            }

            return new Employee(id, name, preferences, contactDetails);
        }

    }
}
