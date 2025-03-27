package com.cheffest.customer_service_api100.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CartRequest {
    private UUID userId;
    private UUID foodId;
    private int sum;

    // Constructor
    public CartRequest() {}

    public CartRequest(UUID userId, UUID foodId, int sum) {
        this.userId = userId;
        this.foodId = foodId;
        this.sum = sum;
    }
}
