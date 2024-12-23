/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.Order;
import manager.SimpleKiosk;

/**
 *
 * @author nemo
 */
public class OrderScreen implements KioskScreen {

    @Override
    public KioskScreen show(Context c) {
        c.setOrder(new Order());
        SimpleKiosk k = c.getKiosk();
        k.clearScreen();
        this.configureScreenButtons(k);
        char res = k.waitEvent(60);
        System.out.println(res);
        
        return new SectionScreen();
    }
    
    private void configureScreenButtons(SimpleKiosk kiosk) {
            kiosk.setTitle("¿Qué quieres hacer?");
            kiosk.setOption(0, "Añadir menú al pedido");
            kiosk.setOption(1, "Añadir producto individual a pedido");
            kiosk.setOption(2, "Elimiar producto");
            kiosk.setOption(4, "Terminar pedido");
            kiosk.setOption(5, "Cancelar Pedido");
            kiosk.setDescription("PEDIDO 20 :");
    }
}
