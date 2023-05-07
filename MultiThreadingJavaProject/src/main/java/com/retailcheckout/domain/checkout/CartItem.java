package com.retailcheckout.domain.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    private Integer itemId;
    private String itemName;
    private double rate;
    private int quantity;
    private boolean isExpired;
}