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
 * @author cibermarcoa
 */
public class OrderScreen implements KioskScreen {

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        
        if (c.getOrder() == null)
            c.setOrder(new Order());
        
        this.configureScreenButtons(k, c);
        
        char res = k.waitEvent(60);
        System.out.println(res);
        
        if (res == 'A')
            return new MenuScreen();  
        else if (res == 'B')
            return new SectionScreen();  
        else if (res == 'C')
            return new PurchaseScreen();  
        else if (res == 'D') {
            c.setOrder(new Order());
            return new WellcomeScreen();  
        } else if (res == 'E')
            return new RemoveProductScreen(); 
        else if (res == '1') { // Gestionar que se meta una tarjeta cuando no procede
            k.retainCard(false);
            k.expelCreditCard(0);
            return this.show(c);
        }
        
        return null;
    }
    
    private void configureScreenButtons(SimpleKiosk kiosk, Context context) {
        kiosk.setMode(1);
        kiosk.clearScreen();
        kiosk.setTitle("¿Qué quieres hacer?");
        kiosk.setDescription(context.getOrder().getOrderText());
        kiosk.setOption(0, "Añadir menú al pedido", context);
        kiosk.setOption(1, "Añadir producto individual a pedido", context);
        kiosk.setOption(2, "Terminar pedido", context);
        kiosk.setOption(3, "Cancelar Pedido", context);
        kiosk.setOption(4, "Eliminar producto", context);
    }
}