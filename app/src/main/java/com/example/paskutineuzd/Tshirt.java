package com.example.paskutineuzd;

public class Tshirt {
    private String tshirtBrand;
    private String tshirtCost;
    private String tshirtDesc;
    private String id;

    public Tshirt(String id, String tshirtBrand, String tshirtCost, String tshirtDesc) {
        this.tshirtBrand = tshirtBrand;
        this.tshirtCost = tshirtCost;
        this.tshirtDesc = tshirtDesc;
        this.id = id;
    }
    public Tshirt(){

    }

    public String getTshirtBrand() {
        return tshirtBrand;
    }

    public void setTshirtBrand(String tshirtBrand) {
        this.tshirtBrand = tshirtBrand;
    }

    public String getTshirtCost() {
        return tshirtCost;
    }

    public void setTshirtCost(String tshirtCost) {
        this.tshirtCost = tshirtCost;
    }

    public String getTshirtDesc() {
        return tshirtDesc;
    }

    public void setTshirtDesc(String tshirtDesc) {
        this.tshirtDesc = tshirtDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
