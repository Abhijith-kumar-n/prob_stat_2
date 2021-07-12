package com.jsonMapper.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Document(collection = "MasterJson")
public class masterJsonMappings {
    Long orderId;
    String masterJson;
}
