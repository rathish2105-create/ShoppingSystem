/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */

    public class CashPayment extends Payment {

    private double cashReceived;

    public CashPayment(double amount, double cashReceived) {
        super(amount);
        this.cashReceived = cashReceived;
    }

    @Override
    public boolean processPayment() {
        return cashReceived >= amount;
    }

    @Override
    public String getPaymentType() {
        return "Cash";
    }

    public double calculateChange() {
        if (cashReceived >= amount) {
            return cashReceived - amount;
        }

        return 0.0;
    }

}
