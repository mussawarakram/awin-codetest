package com.awin.coffeebreak.entity;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeBreakPreference {

    private static List<String> TYPES = List.of("food", "drink");
    private static List<String> DRINK_TYPES = List.of("coffee", "tea");
    private static List<String> FOOD_TYPES = List.of("sandwich", "crisps", "toast");

    Integer id;
    private String type;
    private String subType;
    private StaffMember requestedBy;
    private Instant requestedDate;
    private Map<String, String> details;

    public CoffeeBreakPreference(
          final String type, final String subType, final StaffMember requestedBy, final Map<String, String> details
    ) {
        if (!TYPES.contains(type)) {
            throw new IllegalArgumentException();
        }
        if (type.equals("food")) {
            if (!FOOD_TYPES.contains(subType)) {
                throw new IllegalArgumentException();
            }
        } else {
            if (!DRINK_TYPES.contains(subType)) {
                throw new IllegalArgumentException();
            }
        }

        this.type = type;

        this.requestedBy = requestedBy;
        if(!details.isEmpty()) {
            setDetails(details);
        } else {
            setDetails(new HashMap<>());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(final String subType) {
        this.subType = subType;
    }

    public StaffMember getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(final StaffMember requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Instant getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(final Instant requestedDate) {
        this.requestedDate = requestedDate;
    }

    private void setDetails(final Map<String, String> details) {
        this.details = details;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
