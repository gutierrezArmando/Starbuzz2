package com.armando.starbuzz2;

public class Food {

    private String name;
    private String description;
    private int imageResourceId;


    public static final Food[] foods = {
            new Food("Burritos", "Burritos de pollo", R.drawable.comida_burritos),
            new Food("Chiles Rellenos", "Chiles rellenos de queso", R.drawable.comida_chiles),
            new Food("Donas", "Donas con chocolate", R.drawable.comida_donas)
    };

    private Food(String name, String description, int imageResourceId) {
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
