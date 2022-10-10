package com.example.paskutineuzd;

public class Shoe {
    private String shoeBrand;
    private String shoeCost;
    private String shoeDescription;
    private String id;

    public Shoe(String id,String shoeBrand, String shoeCost, String shoeDescription) {
        this.shoeBrand = shoeBrand;
        this.shoeCost = shoeCost;
        this.shoeDescription = shoeDescription;
        this.id=id;
    }
    public Shoe(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShoeBrand() {
        return shoeBrand;
    }

    public void setShoeBrand(String shoeBrand) {
        this.shoeBrand = shoeBrand;
    }

    public String getShoeCost() {
        return shoeCost;
    }

    public void setShoeCost(String shoeCost) {
        this.shoeCost = shoeCost;
    }

    public String getShoeDescription() {
        return shoeDescription;
    }

    public void setShoeDescription(String shoeDescription) {
        this.shoeDescription = shoeDescription;
    }
}
