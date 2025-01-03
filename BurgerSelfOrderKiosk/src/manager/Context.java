/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import products.Order;
import products.MenuCard;

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
        try {
            this.menuCard = MenuCard.loadFromDisk();
        } catch (RuntimeException e) {
            System.err.println(e);
            this.menuCard = null;
        }
    }

    public SimpleKiosk getKiosk() {
        return this.kiosk;
    }

    public TranslatorManager getTranslator() {
        return this.translator;
    }

    public Order getOrder() {
        return this.order;
    }

    public MenuCard getMenuCard() {
        return this.menuCard;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public void appendOrderToListFile(int orderNumber, Order order) {
        try {
            File file = new File("order_list.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); // `true` para modo de adición
            bw.write("Número de Pedido: " + orderNumber + "\n");
            bw.write("Resumen del Pedido:\n");
            bw.write(order.getOrderText());
            bw.write("Total: " + order.getTotalAmount() + "€\n");
            bw.write("==================\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
