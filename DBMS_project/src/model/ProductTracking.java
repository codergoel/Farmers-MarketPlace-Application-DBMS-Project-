package model;

import java.util.Date;

public class ProductTracking {
    private int trackingId;
    private int orderId;
    private String trackingStatus;
    private String estimatedDeliveryDate;
    private String actualDeliveryDate;

    public ProductTracking(int trackingId, int orderId, String trackingStatus, String estimatedDeliveryDate, String actualDeliveryDate) {
        this.trackingId = trackingId;
        this.orderId = orderId;
        this.trackingStatus = trackingStatus;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
    }

    // Getters and Setters
    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }
}
