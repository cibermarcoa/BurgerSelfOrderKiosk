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
 * @author cibermarcoa
 */
public class MenuScreen extends CarouselScreen 
{
        
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        
        // Mostrar opciones del menú principal
        k.setMode(1);
        k.clearScreen();
        k.setTitle("Menú Principal");

        
        for (int i = 0; i < 3; i++) {
            KioskScreen productScreen = new ProductScreen(i);
            productScreen.show(c);
        }
        return new OrderScreen();
    }
}
