/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;
import sienens.BurgerSelfOrderKiosk;

/**
 *
 * @author nemo
 */
import java.util.List;
public class SimpleKiosk {
    private BurgerSelfOrderKiosk kiosk;
    
    public SimpleKiosk() {
        this.kiosk = new BurgerSelfOrderKiosk();
    }
    
    public void setOption(int optionNumber, String message) {
        this.kiosk.setOption(optionNumber, message);
    }

    public void setTitle(String message) {
        this.kiosk.setTitle(message);
    }

    public void setDescription(String message) {
        this.kiosk.setDescription(message);
    }
    public void setMode() { // +setMode(int) ?
        this.kiosk.setMenuMode();
    }
    
    public synchronized char waitEvent(int seconds) {
        return this.kiosk.waitEvent(seconds);
    }
    
    public boolean print(List<String> text) {   // +print(String)
      return this.kiosk.print(text);
    }
    
    public void retainCard(boolean definitely) {
        this.kiosk.retainCreditCard(definitely);
    }
    
    public boolean expelCreditCard(int seconds) {
        return this.expelCreditCard(seconds);
    }
    
    public void clearScreen() {
        this.kiosk.setTitle(null);
        this.kiosk.setImage(null);
        this.kiosk.setDescription(null);
        for (int i = 0; i < 8; i++) {
            this.kiosk.setOption(i, null);
        }
    }
    
    public void setImage(String imageFileName) {
        this.kiosk.setImage(imageFileName);
    }

    public long getCardNumber() {
        return this.kiosk.getCardNumber();
    }
}