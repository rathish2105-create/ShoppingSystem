/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public class MembershipDiscount implements DiscountPolicy {

    private double discountRate;
    private int minimumQuantity;

    public MembershipDiscount(double discountRate, int minimumQuantity) {
        this.discountRate = discountRate;
        this.minimumQuantity = minimumQuantity;
    }

    @Override
    public double calculateDiscount(double subtotal, Customer customer, int totalQuantity) {

        if (customer != null && customer.isMember() && totalQuantity >= minimumQuantity) {

            return subtotal * discountRate;
        }

        return 0.0;
    }    
}
