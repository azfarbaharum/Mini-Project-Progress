public class CartItem {
    private final String name;
    private final double price;
    private int quantity;

    public CartItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 1; // default quantity is 1
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }
}
