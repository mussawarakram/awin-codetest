package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import org.springframework.stereotype.Service;

@Service
public class NotifierEmail implements Notifier {
    @Override
    public void sendNotification(String userId, CoffeeBreakPreference coffeeBreakPreference) {
        //logic to send E-Mail message of preferences to specified userid
    }
}
