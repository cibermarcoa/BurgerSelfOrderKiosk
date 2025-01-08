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
        //Crea el contexto que contiene los objetos principales como el quiosco, pedido...
        Context c = new Context();
        // Crear la pantalla de bienvenida (WellcomeScreen)
        WellcomeScreen ws = new WellcomeScreen();
        // Muestra la pantalla de bienvenida y obtener la siguiente pantalla
        KioskScreen nextScreen = ws.show(c);    // Ejecuta `show()` y retorna la próxima pantalla
        
        // Bucle infinito para manejar la navegación por las pantallas
        while (true) {
            // Mostrar la siguiente pantalla y actualizar `nextScreen` con la próxima pantalla a mostrar
            nextScreen = nextScreen.show(c);
        }
    }
}
