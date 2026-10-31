/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public interface DiscountPolicy {
    
        double calculateDiscount(double subtotal, Customer customer, int totalQuantity);

}
