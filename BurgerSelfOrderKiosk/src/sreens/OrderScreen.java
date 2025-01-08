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
    
    //Muestra la pantalla de opciones del pedido y gestiona la interacción del usuario.
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        
        // Si no hay una orden activa, se crea una nueva
        if (c.getOrder() == null)
            c.setOrder(new Order());
        
        this.configureScreenButtons(k, c);  // Configurar la pantalla con los botones de opciones
        
        char res = k.waitEvent(60);
        System.out.println(res);
        
        // Opciones disponibles para gestionar el pedido
        if (res == 'A') //Añadir un menú
            return new MenuScreen();  
        else if (res == 'B')    //Añadir un producto individual
            return new SectionScreen();  
        else if (res == 'C')    //Terminar el pedido y proceder al pago
            return new PurchaseScreen();  
        else if (res == 'D') {  //Cancelar el pedido y volver al inicio
            c.setOrder(new Order());
            return new WellcomeScreen();  
        } else if (res == 'E')  //Eliminar un producto de la orden actual
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
        kiosk.setTitle("¿Qué quieres hacer?", context.getTranslator());
        kiosk.setDescription(context.getOrder().getOrderText(), context.getTranslator());   //Mostrar el contenido actual del pedido
        kiosk.setOption(0, "Añadir menú al pedido", context.getTranslator());
        kiosk.setOption(1, "Añadir producto individual a pedido", context.getTranslator());
        kiosk.setOption(2, "Terminar pedido", context.getTranslator());
        kiosk.setOption(3, "Cancelar Pedido", context.getTranslator());
        kiosk.setOption(4, "Eliminar producto", context.getTranslator());
    }
}