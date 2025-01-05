/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.SimpleKiosk;
import products.IndividualProduct;
import products.MenuCardSection;
import products.Order;

/**
 *
 * @author cibermarcoa
 */
public class ProductScreen extends CarouselScreen {
    private int section;
    
    public ProductScreen(int section)
    {
        this.section = section;
    }


    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        IndividualProduct ip = null;
        int i = 0;
        
        while (true) {
            this.configureScreenButtons(k);
            k.setOption(4, "Añadir al pedido");
            k.setOption(5, "Cancelar añadir");
            ip = c.getMenuCard().getSection(this.getSection()).getProduct(i);
            k.setTitle(ip.getName());
            k.setDescription(ip.getDescription() + "\n" + ip.getPrice() + "$");
            k.setImage("src/" + ip.getImageFileName());

            this.adjustCarruselButtons(i, c.getMenuCard().getSection(this.getSection()).getNumberOfProducts() - 1, k);

            char res = k.waitEvent(60);
            System.out.println(res);
            
            if (res == 'F')
                return new OrderScreen();
            else if (res == 'G')
                i--;
            else if (res == 'H')
                i++;
            else if (res == 'E') {
                Order currentOrder = c.getOrder();
                if (currentOrder != null) {
                    currentOrder.addProduct(ip);
                    System.out.println("Producto añadido: " + ip.getName());
                } else
                    System.err.println("Error: No hay un pedido activo.");
                return new OrderScreen();
            }
        }
    }
    /*
    private void configureScreenButtons() {
        
        k.setMode(1);
        k.clearScreen();

    }

    public void ProductSelectionScreen(int section) {
        
    }

    */
    private int getSection() {
        return this.section;
    }
}