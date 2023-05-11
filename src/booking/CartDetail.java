package booking;

import java.io.Serializable;

public class CartDetail implements Serializable {
    private static final long serialVersionUID = 3L;
    public static int idUpCart;
    private int id;
    private Cart cart;
    private Menu menu;
    private int quantity;

    public CartDetail() {
    }

    public CartDetail(Cart idCart, Menu menu, int quantity) {
        this.id =++idUpCart;
        this.cart = idCart;
        this.menu = menu;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu product) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id +"," + cart + "," + menu + "," + quantity ;
    }


    public void display() {
        String cartName = (this.cart != null) ? this.cart.getName() : "";
        String cartDate = (this.cart != null) ? String.valueOf(this.cart.getDate()) : "";
        int menuId = (this.menu != null) ? this.menu.getId() : 0;
        String foodName = (this.menu != null) ? this.menu.getFoodName() : "";
        double price = (this.menu != null) ? this.menu.getPrice() : 0.0;

        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%n",
                this.id, cartName, cartDate, menuId, foodName, price, this.quantity);
    }
}
