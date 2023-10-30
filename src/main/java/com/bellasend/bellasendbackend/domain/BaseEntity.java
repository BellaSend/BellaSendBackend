package com.bellasend.bellasendbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@SuperBuilder
@NoArgsConstructor
@Data
public abstract class BaseEntity {
//TODO figure out to auto increment version and auto set time stamps
//    TODO auto id generate for sub doc
    private String _id;
    private Instant creationDateTime;
    private Instant modificationDateTime;
    private Long version = 0L;
}
