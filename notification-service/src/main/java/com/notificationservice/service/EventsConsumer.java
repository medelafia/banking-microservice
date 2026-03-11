package com.notificationservice.service;


import com.notificationservice.events.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventsConsumer {

    @KafkaListener(topics = "transaction-event" , groupId="notification-group")
    public void consume(TransactionEvent event){

    }
    @KafkaListener(topics = "account-deletion-event" , groupId = "notification-group")
    public void consume(AccountDeletionEvent event){

    }
    @KafkaListener(topics = "account-creation-event" , groupId = "notification-group")
    public void consume(AccountCreationEvent event){

    }
    @KafkaListener(topics = "user-creation-event" , groupId = "notification-group")
    public void consume(UserCreationEvent event){
        System.out.println("user creation : " + event.getUserId());
    }
}
