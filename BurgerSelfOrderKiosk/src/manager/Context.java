/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

/**
 *
 * @author cibermarcoa
 */
public class Context {
    private SimpleKiosk kiosk;
    private TranslatorManager translator;
    private Order order;
    private MenuCard menuCard;

    public Context() {
        this.kiosk = new SimpleKiosk();
        this.translator = new TranslatorManager();
        this.order = new Order();
        this.menuCard = new MenuCard();
    }


    

    public SimpleKiosk getKiosk() {
        return kiosk;
    }

    public TranslatorManager getTranslator() {
        return translator;
    }

    public Order getOrder() {
        return order;
    }

    public MenuCard getMenuCard() {
        return menuCard;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
}
