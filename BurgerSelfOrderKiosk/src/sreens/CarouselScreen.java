/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;

/**
 *
 * @author nemo
 */
public abstract class CarouselScreen implements KioskScreen {
        @Override
    public abstract KioskScreen show(Context c);
    
    protected abstract void adjustCarruselButtons(int currentElement, int numberOfElements);
    protected abstract void configureScreenButtons();
}
