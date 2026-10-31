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

public class Receipt {

    private String receiptId;
    private Customer customer;
    private List<CartItem> purchasedItems;
    private double subtotal;
    private double discount;
    private double total;
    private String paymentType;

    public Receipt(String receiptId, Customer customer, List<CartItem> purchasedItems,
                   double subtotal, double discount, double total, String paymentType) {

        this.receiptId = receiptId;
        this.customer = customer;
        this.purchasedItems = new ArrayList<>(purchasedItems);
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
        this.paymentType = paymentType;

    }

    public void generateReceipt() {
        displayReceipt();
    }

    public void displayReceipt() {
        System.out.println("========== SHOPPING RECEIPT ==========");
        System.out.println("Receipt ID: " + receiptId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("--------------------------------------");

        for (CartItem item : purchasedItems) {
            System.out.println(item.getProduct().getName()
                    + " x " + item.getQuantity()
                    + " = RM " + String.format("%.2f", item.getSubtotal()));
        }

        System.out.println("--------------------------------------");
        System.out.printf("Subtotal: RM %.2f%n", subtotal);
        System.out.printf("Discount: RM %.2f%n", discount);
        System.out.printf("Total: RM %.2f%n", total);
        System.out.println("Payment Method: " + paymentType);
        System.out.println("======================================");
    }
    
}
