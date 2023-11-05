package com.example.mycart;

public class GroceryItem {
    private String title;
    private String desc;
    private String imageUrl;

    public GroceryItem() {
        this.imageUrl = "https://cdn-icons-png.flaticon.com/512/1261/1261052.png";
    }

    public GroceryItem(String title) {
        this.title = title;
        this.desc = "NA";
        this.imageUrl = "https://cdn-icons-png.flaticon.com/512/1261/1261052.png";
    }

    public GroceryItem(String title, String desc) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = "https://cdn-icons-png.flaticon.com/512/1261/1261052.png";
    }

    public GroceryItem(String title, String desc, String imageUrl) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "GroceryItem{" + "title='" + title + '\'' + ", desc='" + desc + '\'' + ", imageUrl='" + imageUrl + '\'' + '}';
    }
}
