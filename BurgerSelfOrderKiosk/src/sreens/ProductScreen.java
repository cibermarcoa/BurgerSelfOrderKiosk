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
    
    //Constructor que establece la sección de productos a mostrar.
    public ProductScreen(int section)
    {
        this.section = section;
    }
    
    //Muestra la pantalla de selección de productos de una sección y gestiona la interacción del usuario.
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        IndividualProduct ip = null;
        int i = 0;
        
        while (true) {
            // Configurar botones básicos de la pantalla
            this.configureScreenButtons(k);
            k.setOption(4, "Añadir al pedido", c.getTranslator());
            k.setOption(5, "Cancelar añadir", c.getTranslator());
            
            // Obtener el producto actual de la sección y mostrar su información
            ip = c.getMenuCard().getSection(this.getSection()).getProduct(i);
            k.setTitle(ip.getName(), c.getTranslator());
            k.setDescription(ip.getDescription() + "\n" + ip.getPrice() + "$", c.getTranslator());
            k.setImage("src/" + ip.getImageFileName());
            
            // Ajustar los botones de navegación del carrusel
            this.adjustCarruselButtons(i, c.getMenuCard().getSection(this.getSection()).getNumberOfProducts() - 1, k, c.getTranslator());

            char res = k.waitEvent(60);
            System.out.println(res);
            
            // Opciones del usuario
            if (res == 'F')  // Cancelar y volver al resumen del pedido.
                return new OrderScreen();
            else if (res == 'G')    // Ir al producto anterior si existe.
                i--;
            else if (res == 'H')    // Ir al producto siguiente si existe.
                i++;
            else if (res == 'E') {  // Añadir producto al pedido.
                Order currentOrder = c.getOrder();  // Obtener la orden actual.
                if (currentOrder != null) {
                    currentOrder.addProduct(ip);
                    System.out.println("Producto añadido: " + ip.getName());
                } else
                    System.err.println("Error: No hay un pedido activo.");
                return new OrderScreen();
            }
        }
    }
    
    //Devuelve el índice de la sección actual.
    private int getSection() {
        return this.section;
    }
}