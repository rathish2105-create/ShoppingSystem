/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) throws ShoppingException {
        if (product == null) {
            throw new ShoppingException("Product cannot be null.");
        }

        if (quantity <= 0) {
            throw new ShoppingException("Quantity must be greater than zero.");
        }

        if (!product.checkStock(quantity)) {
            throw new ShoppingException("Insufficient stock available.");
        }

        for (CartItem item : items) {
            if (item.getProduct().getItemId().equalsIgnoreCase(product.getItemId())) {
                int newQuantity = item.getQuantity() + quantity;

                if (!product.checkStock(newQuantity)) {
                    throw new ShoppingException("Insufficient stock available.");
                }

                item.updateQuantity(newQuantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public boolean removeItem(String itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getItemId().equalsIgnoreCase(itemId)) {
                items.remove(i);
                return true;
            }
        }

        return false;
    }

    public void updateItemQuantity(String itemId, int quantity) throws ShoppingException {
        if (quantity <= 0) {
            throw new ShoppingException("Quantity must be greater than zero.");
        }

        for (CartItem item : items) {
            if (item.getProduct().getItemId().equalsIgnoreCase(itemId)) {
                Product product = item.getProduct();

                if (!product.checkStock(quantity)) {
                    throw new ShoppingException("Insufficient stock available.");
                }

                item.updateQuantity(quantity);
                return;
            }
        }

        throw new ShoppingException("Product not found in shopping cart.");
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }

        for (CartItem item : items) {
            System.out.println(item);
        }
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;

        for (CartItem item : items) {
            subtotal += item.getSubtotal();
        }

        return subtotal;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;

        for (CartItem item : items) {
            totalQuantity += item.getQuantity();
        }

        return totalQuantity;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clearCart() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Shopping Cart: " + items.size()
                + " different product(s), "
                + getTotalQuantity() + " item(s), "
                + "Subtotal: RM"
                + String.format("%.2f", calculateSubtotal());
    }    
}
