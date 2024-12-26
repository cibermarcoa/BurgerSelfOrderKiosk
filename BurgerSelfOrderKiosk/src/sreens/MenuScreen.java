/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.SimpleKiosk;
import products.MenuCard;

/**
 *
 * @author nemo
 */
public class MenuScreen implements KioskScreen {

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        char res = '\0';
        
        // Mostrar opciones del menú principal
        k.clearScreen();
        k.setTitle("Menú Principal");
        k.setDescription("Elige una categoría:");

        // Mostrar opciones
        k.setOption(1, "Hamburguesas");
        k.setOption(2, "Bebidas");
        k.setOption(3, "Complementos");
        k.setOption(4, "Ver Pedido");
        k.setOption(5, "Salir");

        // Esperar la selección del usuario
        res = k.waitEvent(60);

        // Manejar la selección
        if (res == 'A') { // Hamburguesas
            return new SectionScreen();
        } else if (res == 'B') { // Bebidas
            return new SectionScreen();
        } else if (res == 'C') { // Complementos
            return new SectionScreen();
        } else if (res == 'D') { // Ver Pedido
            return new OrderScreen();
        } else if (res == 'E') { // Salir
            return new WellcomeScreen(); 
        }

        return null;
    }
}
