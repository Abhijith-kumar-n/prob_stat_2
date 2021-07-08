package com.jsonMapper.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "userMaps")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class userMapping {

    Long userId;
    String mapping;

}
