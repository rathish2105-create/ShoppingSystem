/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package shoppingsystem;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingSystem {

    /**
     * @param args the command line arguments
     */
    private static Scanner scanner = new Scanner(System.in);
    private static List<Product> products = new ArrayList<>();
    private static CustomerRegistry customerRegistry = new CustomerRegistry();
    
    
    public static void main(String[] args) {
        // TODO code application logic here
         addSampleProducts();
        registerSampleCustomers();

        System.out.println("==================================");
        System.out.println("       SHOPPING SYSTEM");
        System.out.println("==================================");

        boolean running = true;

        while (running) {
            System.out.println("\n1. Register Customer");
            System.out.println("2. View Products");
            System.out.println("3. Shop and Checkout");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    shopAndCheckout();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for shopping!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addSampleProducts() {
        products.add(new Product("P001", "Notebook", "Stationery",
                "A4 notebook", 5.00, 20));
        products.add(new Product("P002", "Pen", "Stationery",
                "Blue ink pen", 2.00, 30));
        products.add(new Product("P003", "Water Bottle", "Accessories",
                "Reusable water bottle", 15.00, 10));
        products.add(new Product("P004", "Backpack", "Accessories",
                "School backpack", 45.00, 8));
    }

    private static void registerSampleCustomers() {
        customerRegistry.registerCustomer(
                new Customer("C001", "Alex", true));
        customerRegistry.registerCustomer(
                new Customer("C002", "Sam", false));
    }

    private static void registerCustomer() {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Customer ID cannot be empty.");
            return;
        }

        if (customerRegistry.findCustomer(id) != null) {
            System.out.println("Customer ID already exists.");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }

        System.out.print("Is the customer a member? (yes/no): ");
        boolean member = scanner.nextLine().trim()
                .equalsIgnoreCase("yes");

        customerRegistry.registerCustomer(
                new Customer(id, name, member));

        System.out.println("Customer registered successfully.");
    }

    private static void viewProducts() {
        System.out.println("\n========== AVAILABLE PRODUCTS ==========");

        for (Product product : products) {
            product.displayDetails();
            System.out.println("----------------------------------------");
        }
    }

    private static void shopAndCheckout() {
        Customer customer;

        while (true) {
            System.out.print("Enter customer ID: ");
            String customerId = scanner.nextLine().trim();

            customer = customerRegistry.findCustomer(customerId);

            if (customer != null) {
                break;
            }

            System.out.println(
                    "Customer not found. Please enter a valid customer ID.");
        }

        ShoppingCart cart = new ShoppingCart();
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n========== SHOPPING MENU ==========");
            System.out.println("1. View Products");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Item Quantity");
            System.out.println("5. Remove Item from Cart");
            System.out.println("6. Finish Shopping and Checkout");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;

                case 2:
                    addItemToCart(cart);
                    break;

                case 3:
                    System.out.println("\n========== YOUR CART ==========");
                    cart.displayItems();
                    System.out.println(cart);
                    break;

                case 4:
                    updateCartItem(cart);
                    break;

                case 5:
                    removeCartItem(cart);
                    break;

                case 6:
                    shopping = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        if (cart.isEmpty()) {
            System.out.println("No items purchased.");
            return;
        }

        System.out.println("\n========== YOUR CART ==========");
        cart.displayItems();

        MembershipDiscount discountPolicy =
                new MembershipDiscount(0.05, 3);

        CheckoutRegister checkout =
                new CheckoutRegister(cart, customer, discountPolicy);

        double subtotal = cart.calculateSubtotal();
        double discount = checkout.calculateDiscount();
        double total = checkout.calculateTotal();

        System.out.printf("Subtotal: RM %.2f%n", subtotal);
        System.out.printf("Discount: RM %.2f%n", discount);
        System.out.printf("Total: RM %.2f%n", total);

        boolean paymentCompleted = false;

        while (!paymentCompleted) {
            System.out.println("\n========== PAYMENT ==========");
            System.out.println("1. Cash");
            System.out.println("2. Card");
            System.out.print("Choose payment method: ");

            int paymentChoice = readInt();
            Payment payment;

            if (paymentChoice == 1) {
                System.out.printf("Amount to pay: RM %.2f%n", total);
                System.out.print("Enter cash received: RM ");
                double cashReceived = readDouble();

                payment = new CashPayment(total, cashReceived);

            } else if (paymentChoice == 2) {
                System.out.print("Enter the last four card digits: ");
                String cardDigits = scanner.nextLine().trim();

                payment = new CardPayment(total, cardDigits);

            } else {
                System.out.println("Invalid payment method.");
                continue;
            }

            try {
                checkout.checkout(payment);
                System.out.println("Checkout completed successfully.");
                paymentCompleted = true;

            } catch (ShoppingException e) {
                System.out.println("Checkout failed: " + e.getMessage());

                System.out.print(
                        "Would you like to retry payment? (yes/no): ");
                String retry = scanner.nextLine().trim();

                if (!retry.equalsIgnoreCase("yes")) {
                    System.out.println("Transaction cancelled.");
                    break;
                }
            }
        }
    }

    private static void addItemToCart(ShoppingCart cart) {
        viewProducts();

        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine().trim();

        Product product = findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = readInt();

        try {
            cart.addItem(product, quantity);
            System.out.println("Product added to cart.");
            System.out.println(cart);
        } catch (ShoppingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateCartItem(ShoppingCart cart) {
        if (cart.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }

        cart.displayItems();

        System.out.print("Enter product ID to update: ");
        String productId = scanner.nextLine().trim();

        System.out.print("Enter new quantity: ");
        int quantity = readInt();

        try {
            cart.updateItemQuantity(productId, quantity);
            System.out.println("Cart quantity updated successfully.");
            System.out.println(cart);
        } catch (ShoppingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeCartItem(ShoppingCart cart) {
        if (cart.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }

        cart.displayItems();

        System.out.print("Enter product ID to remove: ");
        String productId = scanner.nextLine().trim();

        if (cart.removeItem(productId)) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }

        System.out.println(cart);
    }

    private static Product findProduct(String productId) {
        for (Product product : products) {
            if (product.getItemId().equalsIgnoreCase(productId)) {
                return product;
            }
        }

        return null;
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid whole number: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }

                System.out.print("Enter a valid non-negative amount: ");
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid amount: ");
            }
        }
    }
    }
    

