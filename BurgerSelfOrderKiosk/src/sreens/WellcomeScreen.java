/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;
import manager.Context;
import manager.SimpleKiosk;

/**
 *
 * @author cibermarcoa
 */
public class WellcomeScreen implements KioskScreen {
    
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        kiosk.clearScreen();
        this.configureScreenButtons(kiosk);
        char res = kiosk.waitEvent(60);
        System.out.println(res);
        if (res == 'A') {
            return new OrderScreen();
        }
        else if (res == 'E') {
            return new IdiomScreen();
        }
        return null;
    }      
    
    private void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.setOption(0, "Nuevo pedido");
        kiosk.setOption(4, "Cambiar idioma");
    }
}

/*
+show(Context)

*/