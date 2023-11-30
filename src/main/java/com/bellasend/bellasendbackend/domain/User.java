package com.bellasend.bellasendbackend.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User extends BaseEntity{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
