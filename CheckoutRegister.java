/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public class CheckoutRegister {
    
    private ShoppingCart cart;
    private Customer customer;
    private DiscountPolicy discountPolicy;

    public CheckoutRegister(ShoppingCart cart, Customer customer,
                            DiscountPolicy discountPolicy) {
        this.cart = cart;
        this.customer = customer;
        this.discountPolicy = discountPolicy;
    }

    public double calculateDiscount() {
        double subtotal = cart.calculateSubtotal();
        int totalQuantity = cart.getTotalQuantity();

        return discountPolicy.calculateDiscount(
                subtotal, customer, totalQuantity);
    }

    public double calculateTotal() {
        double subtotal = cart.calculateSubtotal();
        double discount = calculateDiscount();

        return subtotal - discount;
    }

    public Receipt checkout(Payment payment) throws ShoppingException {

        if (cart.isEmpty()) {
            throw new ShoppingException("Shopping cart is empty.");
        }

        if (payment == null) {
            throw new ShoppingException("Payment method cannot be null.");
        }

        double subtotal = cart.calculateSubtotal();
        double discount = calculateDiscount();
        double total = calculateTotal();

        if (Math.abs(payment.getAmount() - total) > 0.001) {
            throw new ShoppingException(
                    "Payment amount does not match the total.");
        }

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (!product.checkStock(item.getQuantity())) {
                throw new ShoppingException(
                        "Insufficient stock for " + product.getName());
            }
        }

        if (!payment.processPayment()) {
            throw new ShoppingException(
                    "Payment failed. Please try again.");
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceStock(item.getQuantity());
        }

        Receipt receipt = new Receipt(
                "R" + System.currentTimeMillis(),
                customer,
                cart.getItems(),
                subtotal,
                discount,
                total,
                payment.getPaymentType());

        receipt.generateReceipt();

        if (payment instanceof CashPayment) {
            CashPayment cashPayment = (CashPayment) payment;

            System.out.printf("Change: RM %.2f%n",
                    cashPayment.calculateChange());
        }

        cart.clearCart();

        return receipt;
    }
    
}
