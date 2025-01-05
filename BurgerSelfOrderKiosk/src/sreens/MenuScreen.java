/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import java.util.ArrayList;
import java.util.List;
import manager.Context;
import manager.SimpleKiosk;
import products.IndividualProduct;
import products.Menu;
import products.MenuCard;
import products.Order;

/**
 *
 * @author cibermarcoa
 */
public class MenuScreen extends CarouselScreen 
{
        
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        IndividualProduct ip = null;
        List<IndividualProduct> menu = new ArrayList<>();
        
        int section = 0;
        int i = 0;

        while (section < 3) {
            k.setMode(1);
            k.clearScreen();
            ip = c.getMenuCard().getSection(section).getProduct(i);
            k.setTitle(ip.getName());
            k.setDescription(ip.getDescription() + "\n" + ip.getPrice() + "$");
            k.setImage("src/" + ip.getImageFileName());
            k.setOption(4, "Añadir al pedido");
            k.setOption(5, "Cancelar añadir");
            if (i > 0)
                k.setOption(6, "&lt;");
            if (i < c.getMenuCard().getSection(section).getNumberOfProducts() - 1)
                k.setOption(7, "&gt;");
            char res = k.waitEvent(60);
            System.out.println(res);

            if (res == 'F') {
                k.clearScreen();
                k.setTitle("¿Estás seguro?");
                k.setDescription("Se perderá el menú actual.");
                k.setOption(4, "Sí, cancelar");
                k.setOption(5, "No, seguir seleccionando");

                char confirmRes = k.waitEvent(60);
                if (confirmRes == 'E') {
                    return new OrderScreen(); // Confirmación de cancelación
                }
            }else if (res == 'G')
                i--;
            else if (res == 'H')
                i++;
            else if (res == 'E') {
                menu.add(ip);
                System.out.println("Producto añadido: " + ip.getName());
                i = 0;
                section++;
            }
        }

        Order currentOrder = c.getOrder();
        if (currentOrder != null) {
            StringBuilder menuDetails = new StringBuilder("Menú: ");
            for (IndividualProduct productInMenu : menu) {
                menuDetails.append(productInMenu.getName()).append(", ");
            }
            
            if (menuDetails.length() > 0) {
                menuDetails.setLength(menuDetails.length() - 2);
            }

            Menu menuFinal = new Menu(menuDetails.toString(), menu);
            currentOrder.addProduct(menuFinal);  // Añadir el menú al pedido
            System.out.println("Menú añadido al pedido con descuento.");
            return new OrderScreen();  // Volver al resumen del pedido
        } else {
            System.err.println("Error: No hay un pedido activo.");           
        }
        
        return new OrderScreen();
    }
}
