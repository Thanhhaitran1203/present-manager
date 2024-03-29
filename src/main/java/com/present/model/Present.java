package com.present.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "present")
public class Present {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;
    private int price;
    private int ship;
    private String img;

    public Present() {
    }

    public Present(int id, String code, String name, int price, int ship) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.ship = ship;
    }

    public Present(int id, String code, String name, int price, int ship, String img) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
