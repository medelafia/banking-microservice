package com.accountservice.entities;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String id ;
    private String firstName ;
    private String lastName;
    private String email;
}
