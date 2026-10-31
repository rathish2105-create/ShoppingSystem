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

public class CustomerRegistry {

    private List<Customer> customers;

    public CustomerRegistry() {
        customers = new ArrayList<>();
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Total Registered Customers: " + customers.size();
    }    
}
