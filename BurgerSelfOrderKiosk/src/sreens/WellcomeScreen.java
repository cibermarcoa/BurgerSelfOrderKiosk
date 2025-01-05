/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;
import manager.Context;
import manager.SimpleKiosk;

/**
 * Esta clase representa la pantalla de bienvenida del quiosco.
 * Permite al usuario iniciar un nuevo pedido o cambiar el idioma de la interfaz.
 * 
 * @author cibermarcoa
 */
public class WellcomeScreen implements KioskScreen {
     /**
     * Muestra la pantalla de bienvenida y gestiona la selección del usuario.
     *
     * @param c el contexto que contiene la información del quiosco
     * @return la siguiente pantalla a mostrar según la acción seleccionada por el usuario
     */
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk kiosk = c.getKiosk();

        this.configureScreenButtons(kiosk);
        
        char res = kiosk.waitEvent(60);
        System.out.println(res);
        
        if (res == 'A')
            return new OrderScreen();
        else if (res == 'E')
            return new IdiomScreen();
        return null;
    }

     /**
     * Configura los botones de la pantalla de bienvenida con las opciones disponibles.
     *
     * @param kiosk el quiosco donde se configuran las opciones de los botones
     */
    private void configureScreenButtons(SimpleKiosk kiosk) {
        kiosk.setMode(1);
        kiosk.clearScreen();
        kiosk.setOption(0, "Nuevo pedido");
        kiosk.setOption(4, "Cambiar idioma");
    }
}