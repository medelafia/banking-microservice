package com.notificationservice.events;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AccountCreationEvent extends Event {
    private String accountId ;
}
