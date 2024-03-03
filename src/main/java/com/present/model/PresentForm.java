package com.present.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

public class PresentForm {
    private int id;
    private String code;
    private String name;
    private int price;
    private int ship;
    private MultipartFile img;

    public PresentForm() {
    }

    public PresentForm(int id, String code, String name, int price, int ship, MultipartFile img) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.ship = ship;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShip() {
        return ship;
    }

    public void setShip(int ship) {
        this.ship = ship;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
