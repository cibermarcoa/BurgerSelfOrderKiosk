/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.SimpleKiosk;

/**
 *
 * @author nemo
 */
public class SectionScreen implements KioskScreen {

    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        k.clearScreen();
        this.configureScreenButtons(k);
        char res = k.waitEvent(60);
        System.out.println(res);
        return switch (res) {
            case 'A' -> new ProductScreen(0);
            case 'B' -> new ProductScreen(1);
            case 'C' -> new ProductScreen(2);
            default -> null;
        };
        
    }

    protected void adjustCarruselButtons(int currentElement, int numberOfElements) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.setMode();
        kiosk.setOption(0, "Hamburgesa");
        kiosk.setOption(1, "Bebida");
        kiosk.setOption(2, "Complemento");
    }
}
