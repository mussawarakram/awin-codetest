package com.awin.coffeebreak.entity;
import java.time.LocalDate;

public class NotificationRequest {

    public enum NotificationType {
        SLACK,
        EMAIL
    }

    private NotificationType notificationType;
    private LocalDate date;

    public NotificationRequest(NotificationType notificationType, LocalDate localDate) {
        this.notificationType = notificationType;
        this.date = localDate;
    }


    public NotificationRequest(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
