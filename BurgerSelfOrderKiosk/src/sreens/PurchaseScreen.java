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

        // Obtener el número de tarjeta
        long cardNumber = k.getCardNumber();

        // Realizar la operación bancaria
        int totalAmount = c.getOrder().getTotalAmount();
        if (!bank.doOperation(cardNumber, totalAmount)) {
            throw new CommunicationException("Error: Operación rechazada por el banco.");
        }

        // Incrementar número de pedido y generar el ticket
        int orderNumber = incrementOrderNumber();
        writeOrderToFile(orderNumber, c.getOrder());

        // Mostrar confirmación
        k.clearScreen();
        k.setTitle("Pago Exitoso");
        k.setDescription("Tu número de pedido es: " + orderNumber);
        k.waitEvent(60);

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
}
