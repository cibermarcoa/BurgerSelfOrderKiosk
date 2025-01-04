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
public class MenuScreen extends CarouselScreen 
{
    
    private int index;
    
    public MenuScreen(int index)
    {
        this.index = index;
    }
    
    public MenuScreen()
    {
        this.index = 0;
    }
    
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        
        // Mostrar opciones del menú principal
        k.setMode();
        k.clearScreen();
        k.setTitle("Menú Principal");
        k.setDescription("Elige una categoría:");

        
        while (index < 3)
        {
            return new ProductScreen(index, true);
        }
        
        /*
        for (int i = index; i < 3; i++)
        {
            System.out.println("Indice actual" + i);
            return new ProductScreen(i, true);
        }
        */
        
        // Mostrar opciones
        //k.setOption(0, "Iniciar menu");
        /**
        k.setOption(0, "Hamburguesas");
        k.setOption(1, "Bebidas");
        k.setOption(2, "Complementos");
        k.setOption(3, "Salir");
        **/

        // Esperar la selección del usuario
        //char res = k.waitEvent(60);
        /**
        
        
        // Manejar la selección
        if (res == 'A') { // Hamburguesas
            ProductScreen ps = new ProductScreen(0, true);
            //return new ProductScreen(0, true);
            menu[0] = true;
            return showNextProduct(c, menu);
        } else if (res == 'B') { // Bebidas
            return new ProductScreen(1, true);
        } else if (res == 'C') { // Complementos
            return new ProductScreen(2, true);
        } else if (res == 'D') { // Salir
            return new OrderScreen(); 
        }

        return null;
       
        
 
        
        
    }
    
    /**
    public KioskScreen showNextProduct(Context c, boolean[] menu)
    {
        SimpleKiosk k = c.getKiosk();
        
        // Mostrar opciones del menú principal
        k.clearScreen();
        k.setTitle("Menú Principal");
        k.setDescription("Elige una categoría:");
        
        if (menu[0] == true)
        {
            return new ProductScreen(0);
        }
    }
    **/
        return new OrderScreen();
    }
    
}
