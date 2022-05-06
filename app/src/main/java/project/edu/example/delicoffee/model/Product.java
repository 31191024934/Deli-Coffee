package project.edu.example.delicoffee.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String description,name,url,price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product(String description, String name, String url, String price) {
        this.description = description;
        this.name = name;
        this.url = url;
        this.price = price;
    }
    public Product(){

    }
}
