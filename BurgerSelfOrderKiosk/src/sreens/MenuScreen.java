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
        
        // Mostrar opciones del menú principal
        k.clearScreen();
        k.setTitle("Menú Principal");
        k.setDescription("Elige una categoría:");

        // Mostrar opciones
        k.setOption(0, "Hamburguesas");
        k.setOption(1, "Bebidas");
        k.setOption(2, "Complementos");
        k.setOption(3, "Salir");
        

        // Esperar la selección del usuario
        char res = k.waitEvent(60);

        // Manejar la selección
        if (res == 'A') { // Hamburguesas
            return new ProductScreen(0);
        } else if (res == 'B') { // Bebidas
            return new ProductScreen(1);
        } else if (res == 'C') { // Complementos
            return new ProductScreen(2);
        } else if (res == 'D') { // Salir
            return new OrderScreen(); 
        }

        return null;
    }
}
