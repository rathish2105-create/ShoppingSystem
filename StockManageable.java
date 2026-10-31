/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
public interface StockManageable {
    
    boolean checkStock(int quantity);

    void reduceStock(int quantity) throws ShoppingException;
    
}
