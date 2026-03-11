package com.notificationservice.events;


import com.notificationservice.enums.EventType;
import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Event {
    private String eventId;
    private Timestamp timestamp;
}
