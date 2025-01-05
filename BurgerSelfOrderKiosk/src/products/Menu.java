/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.util.List;

/**
 *
 * @author cibermarcoa
 */
public class Menu implements Product {
    private static int discount = 100;
    private String name;
    private List<IndividualProduct> products;

    public Menu(String name, List<IndividualProduct> products) {
        this.name = name;
        this.products = products;
    }
    
    @Override
    public int getPrice() {
        int totalPrice = 0;
        for (IndividualProduct product : products) {
            totalPrice += product.getPrice();
        }
        return (totalPrice - discount);
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public IndividualProduct getProduct(int index) {
        if (index < 0 || index >= products.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return this.products.get(index);
    }
    
    public int getNumProducts() {
        return this.products.size();
    }
    
    public static void setDiscount(int newDiscount) {
        if (newDiscount < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        discount = newDiscount; 
    }

    public static int getDiscount() {
        return discount;
    }
}
