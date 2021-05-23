package com.cajeroAutomatico.data;

public class Billetes {
    private Integer value;      // Key
    private Integer quantity;   // Value

    public Billetes(Integer value, Integer quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
