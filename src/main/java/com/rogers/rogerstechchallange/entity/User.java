package com.rogers.rogerstechchallange.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.lang.annotation.Documented;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    @Id
    private int id;
    private String userName;
    private String password;

}
