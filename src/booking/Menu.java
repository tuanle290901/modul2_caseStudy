package booking;

import java.io.Serializable;


public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String foodName;
    private int price;

    public Menu(int id, String foodName, int price) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return String.format("%-25s%-25s%-25s",
                id, foodName, price);
    }



}