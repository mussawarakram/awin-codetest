package com.awin.coffeebreak.entity;


public class ContactDetails {
    private String emailAddress;
    private String slackId;

    private ContactDetails(String emailAddress, String slackId) {
        this.emailAddress = emailAddress;
        this.slackId = slackId;
    }

    String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }

    /**
     *  Allows the construction of an ContactDetails object. Fields are nat mandatory.
     */
    public static class Builder {
        String emailAddress;
        String slackId;

        public static Builder start() {
            return new Builder();
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withSlackId(String slackId) {
            this.slackId = slackId;
            return this;
        }

        public ContactDetails build() {
            return new ContactDetails(emailAddress, slackId);
        }

    }
}

