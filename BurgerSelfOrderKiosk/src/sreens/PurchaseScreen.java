/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

/**
 *
 * @author cibermarcoa
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import manager.Context;
import manager.SimpleKiosk;
import products.Order;
import urjc.UrjcBankServer;

public class PurchaseScreen implements KioskScreen {
     /**
     * Muestra la pantalla de compra y gestiona el flujo de pago.
     * 
     * @param c Contexto de la aplicación que incluye la orden y el quiosco.
     * @return La próxima pantalla a mostrar dependiendo del resultado.
     */
    @Override
    public KioskScreen show(Context c) {
        UrjcBankServer bank = new UrjcBankServer();
        SimpleKiosk k = c.getKiosk();
        Order o = c.getOrder();
        
        this.configureScreenButtons(k, o, c);
        
        // Comprobar si hay comunicación con el banco.
        if (!bank.comunicationAvaiable()) {
            k.setMode(2);
            k.clearScreen();
            k.setTitle("Banco no disponible", c.getTranslator());
            k.setDescription("El banco no está disponible en este momento. Inténtelo más tarde.",c.getTranslator());
            k.waitEvent(5);
            return new OrderScreen(); // Volver a la pantalla de pedido
        }
        
        char res = k.waitEvent(60);
        System.out.println(res);
        
        if (res == '1') {   // Tarjeta introducida
            k.retainCard(false);    // Retener temporalmente la tarjeta.
            try {
                // Realizar la operación bancaria.
                if (bank.doOperation(k.getCardNumber(), o.getTotalAmount())) {  // Proceso de pago exitoso
                    int orderNumber = this.incrementOrderNumber();  // Incrementar el número de pedido.
                    this.writeOrderToFile(orderNumber, o);  // Escribir el pedido en un archivo de ticket.

                    k.setMode(2);
                    k.clearScreen();
                    k.setTitle("Proceso de pago exitoso", c.getTranslator());
                    k.setDescription("Ya puedes recoger tu tarjeta\nTu número de pedido es el " + orderNumber + "\nRecoge el ticket\nPermanece atento a las pantallas", c.getTranslator());
                    k.expelCreditCard(60); // Expulsar la tarjeta tras el pago.

                    // Generar e imprimir ticket
                    ArrayList<String> ticketText = writeOrderToFile(orderNumber, o);
                    k.print(ticketText);
                } else {
                    // Proceso de pago fallido
                    k.setMode(2);
                    k.clearScreen();
                    k.setTitle("Problemas en el proceso de pago", c.getTranslator());
                    k.setDescription("El banco dice que no tienes dinero. Prueba con otra tarjeta.", c.getTranslator());
                    k.expelCreditCard(60);
                }
            } catch (CommunicationException ex) {   // Manejar excepciones de comunicación.
                Logger.getLogger(PurchaseScreen.class.getName()).log(Level.SEVERE, null, ex);
                k.clearScreen();
                k.setTitle("Error de Comunicación", c.getTranslator());
                k.setDescription("No se pudo completar el pago debido a un problema de comunicación con el banco.", c.getTranslator());
                k.waitEvent(60);
                return new OrderScreen(); // Volver a la pantalla de pedido
            }
            c.setOrder(new Order());    // Empezar una orden vacia
            return new WellcomeScreen();    // Volver a la WellcomeScreen
        }
        else if (res == 'A') {  // Cancelar pedido
            c.setOrder(new Order());
            return new WellcomeScreen();
        } else if (res == 'B')  // Cancelar pago
            return new OrderScreen();
        return null;
    }
    
    /**
     * Incrementa el número de pedido almacenado en un archivo.
     * Si el archivo no existe, lo crea y empieza desde 0.
     * 
     * @return El número de pedido incrementado.
     */
    private int incrementOrderNumber() {
        int orderNumber = 0;
        File file = new File("order_number.txt");
        
        // Leer el número actual si existe.
        try {
            // Crear archivo si no existe
            if (!file.exists()) {
                file.createNewFile();
            }

            // Leer el número actual si existe
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                if (line != null && !line.isEmpty()) {
                    orderNumber = Integer.parseInt(line.trim());
                }
            }

            // Incrementar el número y escribirlo de nuevo
            orderNumber++;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(String.valueOf(orderNumber));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Manejo básico de errores
        }
        return orderNumber;
    }

     /**
     * Escribe los detalles de un pedido en un archivo y devuelve su contenido.
     * 
     * @param orderNumber Número del pedido.
     * @param order Orden cuyos detalles serán escritos.
     * @return Una lista de cadenas representando el contenido del ticket.
     */
    private ArrayList<String> writeOrderToFile(int orderNumber, Order order) {
        ArrayList<String> ticketContent = new ArrayList<>();

        try {
            File file = new File("ticket_" + orderNumber + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            ticketContent.add("Número de pedido: " + orderNumber);
            ticketContent.add("\nArtículos comprados");
            ticketContent.add("=====================\n");
            ticketContent.add(order.getOrderText());
            ticketContent.add("=====================\n");
            ticketContent.add("Total: " + order.getTotalAmount() + "€");
            ticketContent.add("");

            for (String line : ticketContent) {
                bw.write(line + "\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ticketContent;
    }
    
    /**
     * Configura los botones de la pantalla de compra.
     * @param k Interfaz del quiosco.
     * @param o Orden actual.
     * @param c Contexto con la información de traducción.
     */
    
    private void configureScreenButtons(SimpleKiosk k, Order o, Context c) {
        k.setMode(2);
        k.clearScreen();
        k.setTitle("Resumen del Pedido", c.getTranslator());
        k.setDescription(o.getOrderText() + "\nTotal: " + o.getTotalAmount() + "€\n\nIntroduce tu tarjeta de crédito", c.getTranslator());
        k.setOption(0, "Cancelar pedido", c.getTranslator());
        k.setOption(1, "Cancelar pago", c.getTranslator());
        
    }
}
