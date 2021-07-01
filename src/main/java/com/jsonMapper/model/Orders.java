package com.jsonMapper.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Orders {

    @Id
    private int orderId;

    private String date;

    private int totalSellingPrice;

    private int totalDiscount;

    private String status;

    private int tenantId;

    @OneToMany(mappedBy = "orderId")
    private List<Item> itemId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

}
