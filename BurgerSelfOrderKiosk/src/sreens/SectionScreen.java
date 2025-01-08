/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.SimpleKiosk;

/**
 * Esta clase representa una pantalla que muestra la eleccion del tipo de producto.
 * Cada sección permite la navegación a una pantalla específica de productos individuales.
 * @author cibermarcoa
 */
public class SectionScreen extends CarouselScreen {

    /**
     * Muestra la pantalla de secciones.
     *
     * @param c el contexto que contiene la información del quiosco y la carta de menú
     * @return la siguiente pantalla a mostrar según la interacción del usuario
     */
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        
        // Configurar la pantalla con botones básicos y las opciones específicas de la pantalla de secciones.
        super.configureScreenButtons(k);
        this.configureScreenButtons(k, c);
        
        char res = k.waitEvent(60);
        System.out.println(res);
        
        // Devolver la pantalla correspondiente según la opción seleccionada.
        return switch (res) {
            case 'A' -> new ProductScreen(0);   // Muestra los productos de la sección "Hamburguesas".
            case 'B' -> new ProductScreen(1);    // Muestra los productos de la sección "Bebidas".
            case 'C' -> new ProductScreen(2);    // Muestra los productos de la sección "Complementos".
            default -> null;    // Si no se selecciona una opción válida, no se muestra nada.
        };
        
    }

    /**
     * Configura los botones de la pantalla de secciones con las opciones disponibles.
     *
     * @param kiosk el quiosco donde se configuran las opciones de los botones
     * @param c
     */
    

    protected void configureScreenButtons(SimpleKiosk kiosk, Context c) {
        kiosk.setOption(0, "Hamburguesa", c.getTranslator());    // Opción para seleccionar hamburguesas.
        kiosk.setOption(1, "Bebida", c.getTranslator());    // Opción para seleccionar bebidas.
        kiosk.setOption(2, "Complemento", c.getTranslator());    // Opción para seleccionar complementos.
    }   
}
