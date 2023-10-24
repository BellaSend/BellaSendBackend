package com.bellasend.bellasendbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Data
public abstract class User extends BaseEntity{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
