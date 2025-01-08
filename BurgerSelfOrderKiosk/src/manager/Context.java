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
    private SimpleKiosk kiosk;  //Representa el quiosco donde se muestran las pantallas
    private TranslatorManager translator;   //Manejador de idiomas para traducir textos
    private Order order;    //Pedido actual del usuario
    private MenuCard menuCard;  //Carta de menu con las selecciones de productos

    public Context() {
        this.kiosk = new SimpleKiosk(); //Inicialización del quiosco
        this.translator = new TranslatorManager();  //Inicialización del gestor de idiomas
        this.order = new Order();   //Pedido inicial vacío
        try {
            //Cargar la carta de menú desde el disco
            this.menuCard = MenuCard.loadFromDisk();    
        } catch (RuntimeException e) {
            //Si falla la carga, mostrar el error y asignar null
            System.err.println(e);
            this.menuCard = null;
        }
    }

    public SimpleKiosk getKiosk() {
        return this.kiosk;  //Devuelve el quiosco
    }

    public TranslatorManager getTranslator() {
        return this.translator; //Devuelve el traductor
    }

    public Order getOrder() {
        return this.order;  //Devuelve la orden actual
    }

    public MenuCard getMenuCard() {
        return this.menuCard;   //Devuelve la carta del menú
    }
    
    public void setOrder(Order order) {
        this.order = order; //Establece una nueva orden
    }
    
    
    //Añade la orden actual al archivo de lista de órdenes
    //Este archivo guarda un registro de todas las órdenes realizadas
    public void appendOrderToListFile(int orderNumber, Order order) {
        try {
            File file = new File("order_list.txt"); //Archivo donde se guarda la lista de órdenes
            if (!file.exists()) {
                file.createNewFile();   // Crear el archivo si no existe
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); // `true` para modo de adición
            // Escribir los detalles de la orden
            bw.write("Número de Pedido: " + orderNumber + "\n");    
            bw.write("Resumen del Pedido:\n");
            bw.write(order.getOrderText()); // Texto con los productos y precios del pedido
            bw.write("Total: " + order.getTotalAmount() + "€\n");
            bw.write("==================\n");
            bw.close(); // Cerrar el archivo para guardar los cambios
        } catch (IOException e) {
            // Mostrar el error si ocurre un problema con el archivo
            e.printStackTrace();
        }
    }
}
