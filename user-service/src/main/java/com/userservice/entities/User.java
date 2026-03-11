package com.userservice.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    private String id ;
    private String firstName ; ;
    private String lastName ;
    private String email ;
    private String phoneNumber ;

}
