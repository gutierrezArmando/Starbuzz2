package com.armando.starbuzz2;

public class Store {

    private String name;
    private String description;
    private int imageResourceId;

    public static final Store[] stores = {
            new Store("Aurrera", "Tienda departamental", R.drawable.tienda_aurrera),
            new Store("Coopel", "Tienda de blancos", R.drawable.tienda_coopel),
            new Store("Oxxo", "Super oxxo", R.drawable.tienda_oxxo)
    };

    private Store(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public String toString() {
        return this.name;
    }
}
