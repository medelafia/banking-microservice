package com.notificationservice.service;


import com.notificationservice.enums.NotificationEventType;
import com.notificationservice.events.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventsConsumer {

    @KafkaListener(topics = "notifications-topic" , groupId="notification-group")
    public void consume(NotificationEvent notificationEvent){
        if(notificationEvent.getNotificationEventType() == NotificationEventType.TRANSACTION_EVENT)  {

        }
    }
}
