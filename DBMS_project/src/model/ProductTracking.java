package model;

import java.util.Date;

public class ProductTracking {
    private int trackingId;
    private int orderId;
    private String trackingStatus;
    private Date estimatedDeliveryDate;
    private Date actualDeliveryDate;

    public ProductTracking(int trackingId, int orderId, String trackingStatus, Date estimatedDeliveryDate, Date actualDeliveryDate) {
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

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }
}
