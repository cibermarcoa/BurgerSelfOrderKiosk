/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import products.Order;
import manager.SimpleKiosk;

/**
 *
 * @author nemo
 */
public class OrderScreen implements KioskScreen {

    @Override
    public KioskScreen show(Context c) {
        if (c.getOrder() == null) {
            c.setOrder(new Order());
        }
        SimpleKiosk k = c.getKiosk();
        k.setMode();
        k.clearScreen();
        this.configureScreenButtons(k);
        k.setDescription(c.getOrder().getOrderText());
        char res = k.waitEvent(60);
        System.out.println(res);
        
        if (res == 'A') {
                return new MenuScreen();  
            } else if (res == 'B') {
                return new SectionScreen();  
            }  else if (res == 'C') {
                return new PurchaseScreen();  
            } else if (res == 'D') {
                c.setOrder(new Order());
                return new WellcomeScreen();  
            }
;           return null;
        }
    
    private void configureScreenButtons(SimpleKiosk kiosk) {
            kiosk.setTitle("¿Qué quieres hacer?");
            kiosk.setOption(0, "Añadir menú al pedido");
            kiosk.setOption(1, "Añadir producto individual a pedido");
            kiosk.setOption(2, "Terminar pedido");
            kiosk.setOption(3, "Cancelar Pedido");
    }
}