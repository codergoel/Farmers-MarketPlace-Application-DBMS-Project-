package model;

import java.math.BigDecimal;

public class Product {
    private int pId;
    private int fId; // Farmer ID
    private String name;
    private int pQuantity;
    private BigDecimal pPrice;
    private int pCatId; // Category ID
    private String pDesc; // Product Description

    public Product(int pId, int fId, String name, int pQuantity, BigDecimal pPrice, int pCatId, String pDesc) {
        this.pId = pId;
        this.fId = fId;
        this.name = name;
        this.pQuantity = pQuantity;
        this.pPrice = pPrice;
        this.pCatId = pCatId;
        this.pDesc = pDesc;
    }

    // Getters and Setters
    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public int getFId() {
        return fId;
    }

    public void setFId(int fId) {
        this.fId = fId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPQuantity() {
        return pQuantity;
    }

    public void setPQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public BigDecimal getPPrice() {
        return pPrice;
    }

    public void setPPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public int getPCatId() {
        return pCatId;
    }

    public void setPCatId(int pCatId) {
        this.pCatId = pCatId;
    }

    public String getPDesc() {
        return pDesc;
    }

    public void setPDesc(String pDesc) {
        this.pDesc = pDesc;
    }
}
