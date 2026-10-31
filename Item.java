/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public abstract class Item {
    protected String itemId;
    protected String name;
    protected String category;
    protected String description;
    protected double price;

    public Item(String itemId, String name, String category,
                String description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}
