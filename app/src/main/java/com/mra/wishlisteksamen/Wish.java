package com.mra.wishlisteksamen;

public class Wish {

    String id;
    String title;
    String details;
    String category;
    String price;

    public Wish() {
    }

    public Wish(String id, String title, String details, String category, String price) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
