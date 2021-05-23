package com.awin.coffeebreak.services;

import com.awin.coffeebreak.entity.CoffeeBreakPreference;
import org.springframework.stereotype.Service;

@Service
public class SlackNotifier implements Notifier {
    @Override
    public void sendNotification(String userId, CoffeeBreakPreference coffeeBreakPreference) {
        //logic to send Slack message of preferences to specified userid
    }
}
