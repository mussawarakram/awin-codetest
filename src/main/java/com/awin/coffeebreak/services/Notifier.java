package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;

public interface Notifier {
    /**
     * @param userId the user identity to be used by the notification service
     * @param coffeeBreakPreference the CoffeeBreakPreference to form the message to send
     */
    void sendNotification(String userId, CoffeeBreakPreference coffeeBreakPreference);
}
