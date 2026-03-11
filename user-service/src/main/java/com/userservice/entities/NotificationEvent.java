package com.userservice.entities;


import com.userservice.enums.NotificationEventType;
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
    private NotificationEventType notificationEventType;
    private String message ;
    private String email ;
}
