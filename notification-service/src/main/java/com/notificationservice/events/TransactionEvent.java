package com.notificationservice.events;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TransactionEvent extends Event {
    private String transactionId;
    private String transactionType;
    private String senderId ;
    private String recipientId ;
}
