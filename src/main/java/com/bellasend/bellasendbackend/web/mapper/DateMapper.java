
package com.bellasend.bellasendbackend.web.mapper;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {
    public Instant toInstant(OffsetDateTime offsetDateTime){
        return offsetDateTime != null ? offsetDateTime.toInstant() : null;
    }

    public OffsetDateTime toOffsetDateTime(Instant instant){
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
