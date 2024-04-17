package model;

import java.util.Date;

public class ProductReview {
    private int reviewId;
    private int productId;
    private int userId;
    private String reviewText;
    private Date reviewDate;
    private int rating;

    public ProductReview(int reviewId, int productId, int userId, String reviewText, Date reviewDate, int rating) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.userId = userId;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.rating = rating;
    }

    // Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
