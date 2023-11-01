package com.bellasend.bellasendbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@SuperBuilder
@NoArgsConstructor
@Data
public  class BaseEntity {
//    TODO auto id generate for sub doc
    @Id
    private String id;

    @CreatedDate
    private Instant creationDateTime;

    @LastModifiedDate
    private Instant modificationDateTime;

    @Version
    private Long version;
}
