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
 * @author cibermarcoa
 */

    
public class IdiomScreen extends CarouselScreen {

    //Muestra la pantalla de selección de idioma y gestiona la navegación y selección.
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();
        TranslatorManager translatorManager = c.getTranslator();

        // Obtener los idiomas disponibles
        List<String> idioms = translatorManager.getIdioms();


        int i = 0;
        while (true) {
            // Configurar pantalla con el idioma actual
            super.configureScreenButtons(kiosk);
            kiosk.setTitle("Seleccionar Idioma", translatorManager);
            kiosk.setDescription(idioms.get(i), translatorManager);
            kiosk.setOption(4, "Seleccionar", translatorManager);
            kiosk.setOption(5, "Cancelar", translatorManager);
            // Ajustar botones de carrusel
            this.adjustCarruselButtons(i, idioms.size() - 1, kiosk, translatorManager);

            // Esperar evento
            char res = kiosk.waitEvent(60);
            System.out.println(res);

            if (res == 'E') { // Seleccionar idioma
                translatorManager.setCurrentIdiom(idioms.get(i));
                return new WellcomeScreen(); // Volver a la pantalla principal
            } else if (res == 'F') { // Cancelar
                return new WellcomeScreen(); // Regresar sin cambios
            } else if (res == 'G') { // Ir a idioma anterior
                i--;
            } else if (res == 'H') { // Ir al siguiente idioma
                i++;
            }
        }
    }
}
