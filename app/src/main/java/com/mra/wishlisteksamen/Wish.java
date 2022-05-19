package com.mra.wishlisteksamen;

import java.util.Comparator;

public class Wish {

    String id;
    String title;
    String details;
    String category;
    int price;

    public Wish() {
    }

    public Wish(String id, String title, String details, String category, int price) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.category = category;
        this.price = price;
    }

    public static Comparator<Wish> WishNameComparator = new Comparator<Wish>() {
        @Override
        public int compare(Wish wish, Wish t1) {
            return wish.getTitle().compareToIgnoreCase(t1.getTitle());
        }
    };

    public static Comparator<Wish> WishPriceComparator = new Comparator<Wish>() {
        @Override
        public int compare(Wish wish, Wish t1) {
            return wish.getPrice() - t1.getPrice();
        }
    };

    public static Comparator<Wish> WishCategoryComparator = new Comparator<Wish>() {
        @Override
        public int compare(Wish wish, Wish t1) {
            return wish.getCategory().compareToIgnoreCase(t1.getCategory());
        }
    };

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "" + price;
    }
}
