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
/*public class MenuScreen implements KioskScreen {

    private int burgerSelected = -1;  
    private int drinkSelected = -1;    
    private int sideSelected = -1;    

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        k.clearScreen();
        char res = '\0';

        while (burgerSelected == -1 || drinkSelected == -1 || sideSelected == -1) {
            k.clearScreen();
            k.setTitle("Selecciona un producto para tu menú");

            if (burgerSelected == -1) {
                k.setOption(0, "Selecciona una hamburguesa");
            } else {
                k.setOption(0, "Hamburguesa seleccionada");
            }

            if (drinkSelected == -1) {
                k.setOption(1, "Selecciona una bebida");
            } else {
                k.setOption(1, "Bebida seleccionada");
            }

            if (sideSelected == -1) {
                k.setOption(2, "Selecciona un complemento");
            } else {
                k.setOption(2, "Complemento seleccionado");
            }

            res = k.waitEvent(60);

            if (res == 'A' && burgerSelected == -1) {
                burgerSelected = selectProduct(c, 0);  
            } else if (res == 'B' && drinkSelected == -1) {
                drinkSelected = selectProduct(c, 1);  
            } else if (res == 'C' && sideSelected == -1) {
                sideSelected = selectProduct(c, 2);  
            }
        }

        addMenuToOrder(c);

        return new OrderScreen();  
    }

    private int selectProduct(Context c, int section) {
        ProductScreen productScreen = new ProductScreen(section);
        productScreen.show(c);  
        return section;  
    }

    private void addMenuToOrder(Context c) {
        int burgerSelected = 0; 
        int drinkSelected = 1;  
        int sideSelected = 2;   
        
        MenuCard menuCard = MenuCard.loadFromDisk();  

        c.getOrder().addMenu(burgerSelected, drinkSelected, sideSelected, menuCard);
    }

}
*/