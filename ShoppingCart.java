import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        for (CartItem cartItem : items) {
            if (cartItem.getName().equals(item.getName())) {
                cartItem.incrementQuantity(); // Increment quantity if item already exists
                return;
            }
        }
        items.add(item); // Add item if not already in the cart
    }

    public void removeItem(String itemName) {
        for (CartItem item : items) {
            if (item.getName().equals(itemName)) {
                if (item.getQuantity() > 1) {
                    item.decrementQuantity(); // Decrease quantity if greater than 1
                } else {
                    items.remove(item); // Remove item completely if quantity is 1
                }
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return items; // This should return List<CartItem>
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity(); // Total with quantity
        }
        return total;
    }

    public void clear() {
        items.clear(); // Clear the cart
    }
}
