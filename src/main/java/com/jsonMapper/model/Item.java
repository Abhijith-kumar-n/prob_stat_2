package com.jsonMapper.model;;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Item {

    @Id
    private int itemId;

    private int itemPrice;

    private int itemDiscount;

    private String status;

    private int skuId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Orders orderId;

}
