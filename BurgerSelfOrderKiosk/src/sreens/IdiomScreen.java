/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import java.util.List;
import manager.Context;
import manager.SimpleKiosk;
import manager.TranslatorManager;

/**
 *
 * @author nemo
 */

    
/*public class IdiomScreen extends CarouselScreen {

    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        TranslatorManager translatorManager = c.getTranslator();

        // Obtener los idiomas disponibles
        List<String> idioms = translatorManager.getIdioms();

        int currentElement = 0;
        while (true) {
            // Configurar pantalla con el idioma actual
            kiosk.clearScreen();
            kiosk.setTitle("Seleccionar Idioma");
            kiosk.setDescription("Idioma Actual: " + idioms.get(currentElement));
            kiosk.setOption(4, "Seleccionar");
            kiosk.setOption(5, "Cancelar");

            // Ajustar botones de carrusel
            adjustCarruselButtons(currentElement, idioms.size());

            // Esperar evento
            char res = kiosk.waitEvent(60);

            if (res == 'E') { // Seleccionar idioma
                translatorManager.setCurrentIdiom(idioms.get(currentElement));
                return new WellcomeScreen(); // Volver a la pantalla principal
            } else if (res == 'F') { // Cancelar
                return new WellcomeScreen(); // Regresar sin cambios
            } else if (res == 'G' && currentElement > 0) { // Ir a idioma anterior
                currentElement--;
            } else if (res == 'H' && currentElement < idioms.size() - 1) { // Ir al siguiente idioma
                currentElement++;
            }
        }
    }

    @Override
    protected void adjustCarruselButtons(int currentElement, int numberOfElements) {
        SimpleKiosk kiosk = getKiosk();
        if (currentElement > 0) {
            kiosk.setOption(6, "<");
        }
        if (currentElement < numberOfElements - 1) {
            kiosk.setOption(7, ">");
        }
    }

    @Override
    protected void configureScreenButtons() {
        // No es necesario en este caso porque los botones se configuran dinámicamente en `show`
    }
    
    protected SimpleKiosk getKiosk() {
        return this.context.getKiosk(); 
    }
}*/
