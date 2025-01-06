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
public abstract class CarouselScreen implements KioskScreen {
    
    protected void adjustCarruselButtons(int currentElement, int numberOfElements, SimpleKiosk k, Context c) {
        if (currentElement > 0)
            k.setOption(6, "&lt;", c);
        if (currentElement < numberOfElements)
            k.setOption(7, "&gt;", c);
    }

    protected void configureScreenButtons(SimpleKiosk k) {
        k.setMode(1);
        k.clearScreen();
    }
}
