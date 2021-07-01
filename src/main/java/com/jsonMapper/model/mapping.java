package com.jsonMapper.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(collection="mapping")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class mapping{
    @Id
    int userId;
    String orderId;
    String date;
    String totalSellingPrice;
    String totalDiscount;
    String status;
    String tenentId;
    String item;
    String itemId;
    String itemPrice;
    String itemDiscount;
    String itemStatus;
    String skuId;
    String customer;
    String firstName;
    String lastName;
    String middleName;
    String email;
    String phoneNo;
    String pincode;
    String address;
}
