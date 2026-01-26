package com.platzi.pizza.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    Integer getIdOrder();

    LocalDateTime getOrderDate();

    Double getOrderTotal();

    String getCustomerName();

    String getPizzaNames();

}

