/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public class CardPayment extends Payment {

    private String cardLastFour;

    public CardPayment(double amount, String cardLastFour) {
        super(amount);
        this.cardLastFour = cardLastFour;
    }

    @Override
    public boolean processPayment() {
        return cardLastFour != null && cardLastFour.matches("\\d{4}");
    }

    @Override
    public String getPaymentType() {
        return "Card";
    }
    
}
