package com.bellasend.bellasendbackend.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class BaseEntity {
    @Id
    private String id;

    @CreatedDate
    private Instant creationDateTime;

    @LastModifiedDate
    private Instant modificationDateTime;

    @Version
    private Long version;
}
