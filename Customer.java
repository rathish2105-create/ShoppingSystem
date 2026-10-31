/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public class Customer {
    
    private String customerId;
    private String name;
    private boolean member;

    public Customer(String customerId, String name, boolean member) {
        this.customerId = customerId;
        this.name = name;
        this.member = member;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public boolean isMember() {
        return member;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId
                + ", Name: " + name
                + ", Member: " + member;
    }
    
}
