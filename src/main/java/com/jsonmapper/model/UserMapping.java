package com.jsonmapper.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "userMaps")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserMapping {

    Long userId;
    String mapping;

}
