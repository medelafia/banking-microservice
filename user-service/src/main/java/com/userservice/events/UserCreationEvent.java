package com.userservice.events;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserCreationEvent {
    private String eventId;
    private Timestamp timestamp;
    private String userId;
}
