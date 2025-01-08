/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;
import sienens.BurgerSelfOrderKiosk;

/**
 *
 * @author cibermarcoa
 */
import java.util.List;
public class SimpleKiosk {
    private BurgerSelfOrderKiosk kiosk;
    
    public SimpleKiosk() {
        this.kiosk = new BurgerSelfOrderKiosk();    //Inicializa el quiosco físico
    }
    
    public void setOption(int optionNumber, String message, TranslatorManager tm) 
    {
        this.kiosk.setOption(optionNumber, tm.translate(message));  //Establece una opción de menú en la pantalla del quiosco.
    }

    public void setTitle(String message, TranslatorManager tm) 
    {
        this.kiosk.setTitle(tm.translate(message)); //Traducir y mostrar el título
    }

    public void setDescription(String message, TranslatorManager tm) {
        this.kiosk.setDescription(tm.translate(message));   //Traducir y mostrar la descripción
    }
    public void setMode(int mode) {
        if (mode == 1)
            this.kiosk.setMenuMode();   //Modo de seleccion de menú(para seleccionar productos)
        else if (mode == 2)
            this.kiosk.setMessageMode();    //Modo de mensaje
    }
    
    public synchronized char waitEvent(int seconds) {
        return this.kiosk.waitEvent(seconds);   //Esperar interacción del usuario
    }
    
    public boolean print(List<String> text) {   //Imprimir ticket
      return this.kiosk.print(text);
    }
    
    public void retainCard(boolean definitely) {
        this.kiosk.retainCreditCard(definitely);    //Retener la tarjeta
    }
    
    public boolean expelCreditCard(int seconds) {
        return this.kiosk.expelCreditCard(seconds); //Expulsar la tarjeta
    }
    
    public void clearScreen() { 
    //Borra la pantalla del quiosco(título, iamgen y descripción)
        this.kiosk.setTitle(null);
        this.kiosk.setImage(null);
        this.kiosk.setDescription(null);
        for (int i = 0; i < 8; i++) {   //Limpia las opciones de menú
            this.kiosk.setOption(i, null);
        }
    }
    
    public void setImage(String imageFileName) {
        this.kiosk.setImage(imageFileName); //Establece una imagen
    }

    public long getCardNumber() {
        return this.kiosk.getCardNumber();  //Obtiene el numero de tarjeta
    }
}