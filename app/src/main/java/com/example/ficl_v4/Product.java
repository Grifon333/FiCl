package com.example.ficl_v4;

import java.util.Objects;

public class Product {
    private String name;
    private int protein;
    private int fat;
    private int carbohydrate;
    private int id_manufacturer;

    public Product(String name, int protein, int fat, int carbohydrate, int id_manufacturer) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.id_manufacturer = id_manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getId_manufacturer() {
        return id_manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return protein == product.protein && fat == product.fat && carbohydrate == product.carbohydrate && id_manufacturer == product.id_manufacturer && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, protein, fat, carbohydrate, id_manufacturer);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrate=" + carbohydrate +
                ", id_manufacturer=" + id_manufacturer +
                '}';
    }
}
