package com.notificationservice.events;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserCreationEvent extends Event {
    private String userId;
}
