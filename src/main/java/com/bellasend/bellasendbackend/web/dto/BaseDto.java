package com.bellasend.bellasendbackend.web.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDto{
    @Null
    private String _id;
    @Null
    private OffsetDateTime creationDateTime;
    @Null
    private OffsetDateTime modificationDateTime;
    @Null
    private Long version;

}
