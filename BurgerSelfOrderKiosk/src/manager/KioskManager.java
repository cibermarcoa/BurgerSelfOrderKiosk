/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import sreens.*;

/**
 *
 * @author cibermarcoa
 */
public class KioskManager {
    public void start() {
        Context c = new Context();
        WellcomeScreen ws = new WellcomeScreen();
        
        KioskScreen nextScreen = ws.show(c);
        
        while (true) {
            nextScreen = nextScreen.show(c);
        }
    }
}
