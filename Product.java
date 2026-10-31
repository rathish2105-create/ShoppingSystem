/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
    public class Product extends Item implements StockManageable {

    private int quantityInStock;

    public Product(String itemId, String name, String category,
                   String description, double price, int quantityInStock) {
        super(itemId, name, category, description, price);
        this.quantityInStock = quantityInStock;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        }
    }

    @Override
    public boolean checkStock(int quantity) {
        return quantity > 0 && quantityInStock >= quantity;
    }

    @Override
    public void reduceStock(int quantity) throws ShoppingException {
        if (quantity <= 0) {
            throw new ShoppingException("Quantity must be greater than zero.");
        }

        if (quantity > quantityInStock) {
            throw new ShoppingException("Insufficient stock available.");
        }

        quantityInStock = quantityInStock - quantity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
        System.out.println("Price: RM" + String.format("%.2f", price));
        System.out.println("Quantity in Stock: " + quantityInStock);
    }

    @Override
    public String toString() {
        return "Product ID: " + itemId
                + ", Name: " + name
                + ", Price: RM" + String.format("%.2f", price)
                + ", Stock: " + quantityInStock;
    }
}
