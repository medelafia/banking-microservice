package com.accountservice.dto;



import com.accountservice.enums.NotificationEventType;
import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NotificationEvent {
    private String eventId;
    private Timestamp timestamp;
    private NotificationEventType eventType;
    private String message ;
    private String email ;
}
