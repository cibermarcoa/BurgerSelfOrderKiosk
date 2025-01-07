/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import java.util.List;
import manager.Context;
import manager.SimpleKiosk;
import products.Order;
import products.Product;

/**
 *
 * @author hugo
 */
public class RemoveProductScreen extends CarouselScreen {

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        Order currentOrder = c.getOrder();
        
        if(currentOrder == null || currentOrder.getProducts().isEmpty()){
            this.configureScreenButtons(k);
            k.setTitle("Eliminar Producto", c.getTranslator());
            k.setDescription("La orden está vacía. No hay productos que eliminar.");
            k.setOption(4, "Volver", c.getTranslator());
            k.waitEvent(60);
            return new OrderScreen();
        }
        
        int currentIndex = 0;
        List<Product> products = currentOrder.getProducts();
        
        while (true) {
            Product productToDelete = products.get(currentIndex);

            k.clearScreen();
            k.setTitle("Eliminar Producto", c.getTranslator());
            k.setDescription("¿Eliminar: " + productToDelete.getName() + "?");
            k.setOption(4, "Sí, eliminar", c.getTranslator());
            k.setOption(5, "No, cancelar", c.getTranslator());
            this.adjustCarruselButtons(currentIndex, products.size() - 1, k, c.getTranslator());

            char res = k.waitEvent(60);
            System.out.println(res);

            if (res == 'E') {  
                products.remove(currentIndex);
                System.out.println("Producto eliminado: " + productToDelete.getName());
/*
                if (products.isEmpty()) {
                    k.setDescription("La orden está vacía.");
                    k.waitEvent(60);
                    
                }*/
                return new OrderScreen();
                // currentIndex = Math.min(currentIndex, products.size() - 1);  
            } else if (res == 'F') {  
                return new OrderScreen();  
            } else if (res == 'G' && currentIndex > 0) { 
                currentIndex--;
            } else if (res == 'H' && currentIndex < products.size() - 1) { 
                currentIndex++;
            }
        }
    }  
}
