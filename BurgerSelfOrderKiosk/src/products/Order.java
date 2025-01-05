/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cibermarcoa
 */
public class Order {
    private int orderNumber;
    private List<Product> products;

    // Constructor
    public Order() {
        this.orderNumber = 1;
        this.products = new ArrayList<>();
    }

    // Métodos
    public int getTotalAmount() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public String getOrderText() {
        StringBuilder text = new StringBuilder();
        for (Product product : products) {
            text.append(product.getName()).append(": ").append(product.getPrice()).append("\n");
        }
        return text.toString();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    
    public List<Product> getProducts() {
        return products;
    }
}
