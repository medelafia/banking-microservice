package com.notificationservice.events;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AccountDeletionEvent extends Event {
    private String accountId;
    private String purpose;
}
