package com.awin.coffeebreak.entity;

import com.awin.coffeebreak.exceptions.InvalidEmployeeException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Document(collection="employees")
public class Employee {

    @Id
    private Integer id;
    private String name;
    private Map<LocalDate, CoffeeBreakPreference> preferences;
    @JsonIgnore
    private ContactDetails contactDetails;

    private Employee(Integer id, String name, Map<LocalDate, CoffeeBreakPreference> preferences, ContactDetails contactDetails) {
        this.name = name;
        this.id = id;
        this.preferences = preferences;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<LocalDate, CoffeeBreakPreference> getPreference() {
        return preferences;
    }

    public void setPreference(Map<LocalDate, CoffeeBreakPreference> preferences) {
        this.preferences = preferences;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    /**
     * @return a HTML formatted string representation of the object
     */
    public String toHtmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<li>").append("ID: ").append(id).append("</li>");
        sb.append("<li>").append("Name: ").append(name).append("</li>");

        sb.append("<li>").append("Prefences: ").append("<ul>");

        preferences.forEach(
                (date, pref) -> sb.append("<ul>")
                        .append(date.toString())
                        .append(":")
                        .append(pref.toHtmlString())
                        .append("</ul>"));

        sb.append("</ul>").append("</li>");

        return sb.toString();
    }

    /**
     *  Allows the construction of an Employee object. Mandatory fields are name and id.
     */
    public static class Builder {

        private Integer id;
        private String name;
        private ContactDetails contactDetails;
        private Map<LocalDate, CoffeeBreakPreference> preferences = new HashMap<>();

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

        public Builder withPreference(LocalDate date, CoffeeBreakPreference preference) {
            preferences.put(date, preference);
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
