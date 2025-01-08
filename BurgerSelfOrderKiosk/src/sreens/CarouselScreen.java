/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.SimpleKiosk;
import manager.TranslatorManager;

/**
 *
 * @author cibermarcoa
 */
public abstract class CarouselScreen implements KioskScreen {
    
    //Ajusta los botones de navegación en función del elemento actual y la cantidad total.
    protected void adjustCarruselButtons(int currentElement, int numberOfElements, SimpleKiosk k, TranslatorManager tm) {
        if (currentElement > 0)
            k.setOption(6, "&lt;", tm); //Botón anterior
        if (currentElement < numberOfElements)
            k.setOption(7, "&gt;", tm); //Botón siguiente
    }
    
    //Configura la pantalla del quiosco en modo de menú y limpia el contenido anterior.
    protected void configureScreenButtons(SimpleKiosk k) {
        k.setMode(1);
        k.clearScreen();
    }
}
