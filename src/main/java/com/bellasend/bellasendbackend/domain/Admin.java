package com.bellasend.bellasendbackend.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
public class Admin extends User{
}
