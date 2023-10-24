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
//TODO config uuid with mognodb orm
    private String id;
    private Instant creationDateTime;
    private Instant modificationDateTime;
    private Long version;
}
