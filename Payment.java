/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public abstract class Payment implements Payable {

    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    @Override
    public abstract boolean processPayment();

    public abstract String getPaymentType();

    public double getAmount() {
        return amount;
    }
    
}
