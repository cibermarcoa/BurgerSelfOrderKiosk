/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

/**
 *
 * @author nemo
 */

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import manager.Context;
import manager.SimpleKiosk;
import products.Order;
import urjc.UrjcBankServer;

public class PurchaseScreen implements KioskScreen {
    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        k.clearScreen();

        // Mostrar resumen del pedido
        k.setTitle("Resumen del Pedido");
        k.setDescription(c.getOrder().getOrderText() + "\nTotal: " + c.getOrder().getTotalAmount() + "€");
        k.setOption(0, "Pagar");
        k.setOption(1, "Cancelar");

        char res = k.waitEvent(60);

        if (res == 'A') { try {
            // Iniciar el pago
            return processPayment(c);
            } catch (CommunicationException ex) {
                Logger.getLogger(PurchaseScreen.class.getName()).log(Level.SEVERE, null, ex);
                k.clearScreen();
                k.setTitle("Error de Comunicación");
                k.setDescription("No se pudo completar el pago debido a un problema de comunicación con el banco.");
                k.waitEvent(60);
                return new OrderScreen(); // Volver a la pantalla de pedido
            }
        } else if (res == 'B') { // Cancelar pedido
            return new OrderScreen(); // Volver a la pantalla de pedido
        }

        return null;
    }

    private KioskScreen processPayment(Context c) throws CommunicationException {
        SimpleKiosk k = c.getKiosk();
        UrjcBankServer bank = new UrjcBankServer();

        k.clearScreen();
        k.setTitle("Pago");
        k.setDescription("Introduce tu tarjeta de crédito:");

        
        // Esperar hasta que se inserte una tarjeta válida
        long cardNumber = k.getCardNumber();
        while (cardNumber == 0) { // Continuar esperando hasta obtener un número válido
            cardNumber = k.getCardNumber();
            if (cardNumber == 0) {
                k.setDescription("Por favor, introduce una tarjeta válida.");
                k.waitEvent(60); // Esperar para permitir al usuario intentar nuevamente
            }
        }

        // Realizar la operación bancaria
        int totalAmount = c.getOrder().getTotalAmount();
        if (!bank.doOperation(cardNumber, totalAmount)) {
            k.setDescription("Error: Pago rechazado. Verifica tu tarjeta o saldo.");
            k.waitEvent(60);
            return new OrderScreen();
        }

        // Incrementar número de pedido y generar el ticket
        int orderNumber = incrementOrderNumber();
        c.appendOrderToListFile(orderNumber, c.getOrder());
        writeOrderToFile(orderNumber, c.getOrder());
        
        // Imprimir el ticket
        printTicket(k, orderNumber);
        
        // Mostrar confirmación
        k.clearScreen();
        k.setTitle("Pago Exitoso");
        k.setDescription("Tu número de pedido es: " + orderNumber + "\nTarjeta utilizada: " + cardNumber);
        k.waitEvent(10);
            
        return new WellcomeScreen(); // Regresar al inicio       
    }

    private int incrementOrderNumber() {
        int orderNumber = 0;
        try {
            File file = new File("order_number.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            // Leer el número actual
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            if (line != null) {
                orderNumber = Integer.parseInt(line.trim());
            }
            br.close();

            // Incrementar el número y escribirlo de nuevo
            orderNumber++;
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(orderNumber));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderNumber;
    }

    private void writeOrderToFile(int orderNumber, Order order) {
        try {
            File file = new File("ticket_" + orderNumber + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.write("===== TICKET =====\n");
            bw.write("Número de Pedido: " + orderNumber + "\n");
            bw.write("Resumen del Pedido:\n");
            bw.write(order.getOrderText());
            bw.write("\nTotal: " + order.getTotalAmount() + "€\n");
            bw.write("==================\n");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void printTicket(SimpleKiosk kiosk, int orderNumber) {
        try {
            File file = new File("ticket_" + orderNumber + ".txt");
            if (!file.exists()) {
                System.out.println("Error: No se encontró el archivo del ticket.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder ticketContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                ticketContent.append(line).append("\n");
            }
            br.close();

            // Imprimir el contenido del ticket en el quiosco
            kiosk.print(List.of(ticketContent.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
