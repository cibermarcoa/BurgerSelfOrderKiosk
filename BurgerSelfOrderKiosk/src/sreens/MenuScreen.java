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
 * Esta clase representa una pantalla de selección para la creación de un menú.
 * Los usuarios pueden navegar entre productos y añadirlos a un menú, que se agrega al pedido con un descuento.
 * 
 * @author cibermarcoa
 */
public class MenuScreen extends CarouselScreen 
{
     /**
     * Muestra la pantalla de creación de menú y gestiona la selección de productos por parte del usuario.
     *
     * @param c el contexto que contiene el quiosco, la carta del menú y el pedido actual
     * @return la siguiente pantalla a mostrar, típicamente la pantalla con la orden (OrderScreen)
     */
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        IndividualProduct ip = null;
        List<IndividualProduct> menu = new ArrayList<>();
        
        int section = 0;    // Indica la categoría actual: 0 = Hamburguesa, 1 = Bebida, 2 = Complemento
        int i = 0;  // Índice del producto dentro de la sección actual
        
        // Recorrer las 3 categorías del menú
        while (section < 3) {
            this.configureScreenButtons(k);
            
             // Mostrar información del producto actual
            ip = c.getMenuCard().getSection(section).getProduct(i);
            k.setTitle(ip.getName(), c.getTranslator());
            k.setDescription(ip.getDescription() + "\n" + ip.getPrice() + "$", c.getTranslator());
            k.setImage("src/" + ip.getImageFileName());
            k.setOption(4, "Añadir al pedido", c.getTranslator());
            k.setOption(5, "Cancelar añadir", c.getTranslator());
            
            // Botones de navegación del carrusel
            this.adjustCarruselButtons(i, c.getMenuCard().getSection(section).getNumberOfProducts() - 1, k, c.getTranslator());
            
            // Esperar respuesta del usuario
            char res = k.waitEvent(60);
            System.out.println(res);

            if (res == 'F') {    // Cancelar la creación del menú
                k.clearScreen();
                k.setTitle("¿Estás seguro?", c.getTranslator());
                k.setDescription("Se perderá el menú actual.", c.getTranslator());
                k.setOption(4, "Sí, cancelar", c.getTranslator());
                k.setOption(5, "No, seguir seleccionando", c.getTranslator());
                if (k.waitEvent(60) == 'E')
                    return new OrderScreen(); // Confirmación de cancelación
            }
            else if (res == 'G')    // Navegar al producto anterior
                i--;
            else if (res == 'H')    // Navegar al producto siguiente
                i++;
            else if (res == 'E') {  // Añadir el producto al menú
                menu.add(ip);
                System.out.println("Producto añadido: " + ip.getName());
                i = 0;  // Reiniciar el índice al seleccionar la siguiente sección
                section++;  // Avanzar a la siguiente categoría
            }
        }
        
        // Agregar el menú completo al pedido
        Order currentOrder = c.getOrder();
        if (currentOrder != null) {
            StringBuilder menuDetails = new StringBuilder("Menú: ");
            for (IndividualProduct productInMenu : menu) {
                menuDetails.append(productInMenu.getName()).append(", ");
            }
            
            // Eliminar la última coma y espacio
            if (menuDetails.length() > 0) {
                menuDetails.setLength(menuDetails.length() - 2);
            }

            Menu menuFinal = new Menu(menuDetails.toString(), menu);
            currentOrder.addProduct(menuFinal);  // Añadir el menú al pedido con descuento
            System.out.println("Menú añadido al pedido con descuento.");
            return new OrderScreen();  // Volver al resumen del pedido
        } else {
            System.err.println("Error: No hay un pedido activo.");           
        }
        
        return new OrderScreen();   // Regresar al resumen del pedido por defecto
    }
}
