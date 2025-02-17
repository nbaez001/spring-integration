package com.empresa.proyecto.orderprocessing.model;

public class OrderItem {
    private String itemId;
    private String itemType;
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(String itemId, String itemType, int quantity) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId='" + itemId + '\'' +
                ", itemType='" + itemType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
