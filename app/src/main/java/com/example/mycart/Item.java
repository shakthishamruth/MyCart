package com.example.mycart;

public class Item {
    private String title;
    private String desc;
    private String imageUrl;
    private int price;

    public Item() {
        this.imageUrl = "https://static.thenounproject.com/png/1880699-200.png";
        this.price = 0;
    }

    public Item(String title) {
        this.title = title;
        this.desc = "NA";
        this.imageUrl = "https://static.thenounproject.com/png/1880699-200.png";
        this.price = 0;
    }

    public Item(String title, String desc) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = "https://static.thenounproject.com/png/1880699-200.png";
        this.price = 0;
    }

    public Item(String title, String desc, String imageUrl) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.price = 0;
    }

    public Item(String title, String desc, int price) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = "https://static.thenounproject.com/png/1880699-200.png";
        this.price = price;
    }

    public Item(String title, String desc, int price, String imageUrl) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPriceString() {
        return Integer.toString(price);
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "title='" + title + '\'' + ", desc='" + desc + '\'' + ", imageUrl='" + imageUrl + '\'' + '}';
    }
}
