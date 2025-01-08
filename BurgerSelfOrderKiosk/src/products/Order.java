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

    //Calcula el monto total de la orden sumando el precio de cada producto.
    public int getTotalAmount() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    
    //Genera un texto resumen con los detalles de los productos de la orden.
    public String getOrderText() {
        StringBuilder text = new StringBuilder();
        for (Product product : products) {
            text.append(product.getName()).append(": ").append(product.getPrice()).append("\n");
        }
        return text.toString();
    }
    
    //Devuelve el número de pedido.
    public int getOrderNumber() {
        return orderNumber;
    }
    
    //Añade un producto (individual o menú) a la orden.
    public void addProduct(Product product) {
        products.add(product);
    }
    
    //Devuelve la lista de productos en la orden.
    public List<Product> getProducts() {
        return products;
    }
}
