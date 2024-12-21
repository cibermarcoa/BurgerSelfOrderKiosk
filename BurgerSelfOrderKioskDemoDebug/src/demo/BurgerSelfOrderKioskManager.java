package demo;

import java.util.ArrayList;
import java.util.List;
import javax.naming.CommunicationException;
import sienens.BurgerSelfOrderKiosk;
import urjc.UrjcBankServer;

/**
 *
 * @author jvelez
 */
class BurgerSelfOrderKioskManager {
    BurgerSelfOrderKiosk dispenser = new BurgerSelfOrderKiosk();    
    
    UrjcBankServer bank = new UrjcBankServer();
    int mode = 0;
    
    private void clear() {
        dispenser.setTitle(null);//OCULTA EL TITULO
        dispenser.setImage(null);//OCULTA LA IAMGEN
        dispenser.setDescription(null);//OCULTA LA DESCRIPCION
        
        for (int cont = 0; cont < 8; cont++) //OCULTA TODOS LOS BOTONES DEL INTERFAZ
            dispenser.setOption(cont, null);//LIMPIA EL BOTON DE LA POSICIÓN cont
    }
    
    
    void run() {
        
        final int waitTime = 300;
        
        while(true) {
            //INTERFAZ PRINCIPAL
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setMenuMode();
            dispenser.setTitle("URJC Burger - Bienvenido");//PONE EL TITULO A LA VENTANA
            dispenser.setImage("Logo.png");// PRESENTA IMAGEN
            dispenser.setOption(1, "Nuevo pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(4, "Cambiar idioma");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4            
            char respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..) 
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ CAMBIAR IDIOMA
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Logo.png");// PRESENTA IMAGEN
            dispenser.setTitle("Seleccione idioma");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(0, "ESPAÑOL");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0   
            dispenser.setOption(1, "INGLES");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(2, "ALEMAN");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 2          
            dispenser.setOption(3, "...");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 3  
            dispenser.setDescription("ESTOS IDIOMAS NO SON ALGO FIJO DE ESTA APLICACIÓN.\nSE CARGAN DINAMICAMENTE LOS POSIBLES IDIOMAS.\nLOS POSIBLES IDIOMAS DE LA APP SON LOS FICHEROS QUE ENCUENTRE EN UNA CARPETA DE DICCIONARIOS.");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);

            //INTERFAZ PRINCIPAL
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setMenuMode();
            dispenser.setTitle("URJC Burger - Bienvenido");//PONE EL TITULO A LA VENTANA
            dispenser.setImage("Logo.png");// PRESENTA IMAGEN
            dispenser.setOption(1, "Nuevo pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(4, "Cambiar idioma");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4            
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..) 
            System.out.println(respuestaInterfaz);
                        
            //INTERFAZ PEDIDO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Pedido.png");// PRESENTA IMAGEN
            dispenser.setTitle("¿Qué quieres hacer?");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(0, "Añadir menú al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0   
            dispenser.setOption(1, "Añadir producto individual a pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(2, "Elimiar producto");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 2           
            dispenser.setOption(4, "Terminar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4          
            dispenser.setOption(5, "Cancelar Pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5  
            dispenser.setDescription("PEDIDO 20 :");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);

            //INTERFAZ EN EL CARRUSEL DE PRODUCTO
            clear(); //LIMPIA EL INTERFAZ
            dispenser.setTitle("Selecciona producto"); //PONE EL TITULO A LA VENTANA
            dispenser.setImage("Hamburguesa.png"); // PRESENTA IMAGEN
            dispenser.setDescription("Hamburguesa de ternera\n\n100 gr de carne 100% vacuno\nPrecio: 50€"); //AÑADE UNA DESCRIPCION 
            dispenser.setOption(7, "&gt;"); //MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir al pedido"); // AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setOption(5, "Cancelar añadir"); // AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5            
            respuestaInterfaz = dispenser.waitEvent(waitTime); //ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ EN EL CARRUSEL DE PRODUCTO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Pollo.png");// PRESENTA IMAGEN
            dispenser.setTitle("Selecciona producto");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(6, "&lt;");//MUESTRA UNA "<" EN EL BOTON 6. EL BOTON RETROCEDE EL CARRUSEL
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setOption(5, "Cancelar añadir");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5   
            dispenser.setDescription("Hamburguesa de pollo\n\n100 gr de carne 100% pollo\nPrecio: 40€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            
            //INTERFAZ EN EL CARRUSEL DE PRODUCTO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Fanta.png");// PRESENTA IMAGEN
            dispenser.setTitle("Selecciona producto");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(6, "&lt;");//MUESTRA UNA "<" EN EL BOTON 6. EL BOTON RETROCEDE EL CARRUSEL
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setOption(5, "Cancelar añadir");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5   
            dispenser.setDescription("Fanta naranja: 10€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            
            //INTERFAZ EN EL CARRUSEL DE PRODUCTO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Cocacola.png");// PRESENTA IMAGEN
            dispenser.setTitle("Selecciona producto");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(6, "&lt;");//MUESTRA UNA "<" EN EL BOTON 6. EL BOTON RETROCEDE EL CARRUSEL
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setOption(5, "Cancelar añadir");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5   
            dispenser.setDescription("Cocacola normal\n33cc: 10€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ PEDIDO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Pedido.png");// PRESENTA IMAGEN
            dispenser.setTitle("¿Qué quieres hacer?");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(0, "Añadir menú al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0   
            dispenser.setOption(1, "Añadir producto individual a pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(4, "Terminar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4          
            dispenser.setOption(5, "Cancelar Pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5        
            dispenser.setDescription("PEDIDO 20 : \n\n Cocacola normal 33cc: 10€ \n Hamburguesa de ternera 100 gr de carne 100% vacuno : 50€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);

            //INTERFAZ HAMBURGUESA MENU
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Pollo.png");// PRESENTA IMAGEN            
            dispenser.setTitle("Selecciona la hamburguesa del menú"); //PONE EL TITULO A LA VENTANA         
            //dispenser.setOption(6, "&lt;");//MUESTRA UNA "<" EN EL BOTON 6. EL BOTON RETROCEDE EL CARRUSEL
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir Hamburguesa");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 3
            dispenser.setOption(5, "Cancelar menú");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setDescription("Hamburguesa de pollo\n\n100 gr de carne 100% pollo\nPrecio: 40€");;//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ HAMBURGUESA MENU
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Hamburguesa.png");// PRESENTA IMAGEN            
            dispenser.setTitle("Selecciona la hamburguesa del menú"); //PONE EL TITULO A LA VENTANA         
            dispenser.setOption(6, "&lt;");//MUESTRA UNA "<" EN EL BOTON 6. EL BOTON RETROCEDE EL CARRUSEL
            //dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir Hamburguesa");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 3
            dispenser.setOption(5, "Cancelar menú");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setDescription("Hamburguesa de ternera\n\n100 gr de carne 100% pollo\nPrecio: 50€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ BEBIDA MENU
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setTitle("Elige la bebida del menú");//PONE EL TITULO A LA VENTANA   
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir bebida");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 3
            dispenser.setOption(5, "Cancelar menú");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setImage("Cocacola.png");// PRESENTA IMAGEN  
            dispenser.setDescription("Cocacola normal\n33cc");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);

            //INTERFAZ COMPLEMENTO MENU
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setTitle("Elige complemento del menú");//PONE EL TITULO A LA VENTANA         
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir complemento");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 3
            dispenser.setOption(5, "Cancelar menú");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setDescription("Patatas");//AÑADE UNA DESCRIPCION 
            dispenser.setImage("Patatas.png");// PRESENTA IMAGEN 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ COMPLEMENTO MENU
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setTitle("Elige complemento del menú");//PONE EL TITULO A LA VENTANA        
            dispenser.setOption(6, "&lt;");//MUESTRA UNA "<" EN EL BOTON 6. EL BOTON RETROCEDE EL CARRUSEL
            dispenser.setOption(7, "&gt;");//MUESTRA UNA ">" EN EL BOTON 7. EL BOTON AVANZA EL CARRUSEL
            dispenser.setOption(4, "Añadir complemento");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 3
            dispenser.setOption(5, "Cancelar menú");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4
            dispenser.setDescription("Aros de cebolla");//AÑADE UNA DESCRIPCION 
            dispenser.setImage("ArosCebolla.png");// PRESENTA IMAGEN 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);

            //INTERFAZ PEDIDO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Pedido.png");// PRESENTA IMAGEN
            dispenser.setTitle("¿Qué quieres hacer?");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(0, "Añadir menú al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0   
            dispenser.setOption(1, "Añadir producto individual a pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(2, "Elimiar producto");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 2
            dispenser.setOption(4, "Terminar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4          
            dispenser.setOption(5, "Cancelar Pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5          
            dispenser.setDescription("PEDIDO 20 : \nCocacola normal 33cc: 10€\n Hamburguesa de ternera 100 gr : 50€ \n MENU: \n Hamburguesa 10€ \n Cocacola 3€\nPatatas 8€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ TERMINAR PEDIDO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setMessageMode();//EL INTERFAZ SE CAMBIA A INTERFAZ MENSAJE (HASTA AHORA EL INTERFAZA ERA DE TIPO MENU (dispenser.SetMenuMode()))
            dispenser.setTitle("Introduce tu tarjeta de crédito");//PONE EL TITULO A LA VENTANA
            dispenser.setDescription("Pedido actual:\nMenú x1\nCocacola x1\n\nTotal: 200€\n\nIntroduce la tarjeta de crédito para confirmar el pedido o pulsa los botones inferiores para tomar otra decisión");//AÑADE UNA DESCRIPCION             
            dispenser.setOption(0, "Modificar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0  
            dispenser.setOption(1, "Cancelar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)         
            System.out.println(respuestaInterfaz);
            
            //INTERFAZ PEDIDO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setImage("Pedido.png");// PRESENTA IMAGEN
            dispenser.setTitle("¿Qué quieres hacer?");//PONE EL TITULO A LA VENTANA
            dispenser.setOption(0, "Añadir menú al pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0   
            dispenser.setOption(1, "Añadir producto individual a pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1
            dispenser.setOption(2, "Elimiar producto");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 2
            dispenser.setOption(4, "Terminar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 4          
            dispenser.setOption(5, "Cancelar Pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 5          
            dispenser.setDescription("PEDIDO 20 : \nCocacola normal 33cc: 10€\n Hamburguesa de ternera 100 gr : 50€ \n MENU: \n Hamburguesa 10€ \n Cocacola 3€\nPatatas 8€");//AÑADE UNA DESCRIPCION 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)
            System.out.println(respuestaInterfaz);

                        //INTERFAZ TERMINAR PEDIDO
            clear();// LIMPIAR EL INTERFAZ 
            dispenser.setMessageMode();//EL INTERFAZ SE CAMBIA A INTERFAZ MENSAJE (HASTA AHORA EL INTERFAZA ERA DE TIPO MENU (dispenser.SetMenuMode()))
            dispenser.setTitle("Introduce tu tarjeta de crédito");//PONE EL TITULO A LA VENTANA
            dispenser.setDescription("Pedido actual:\nMenú x1\nCocacola x1\n\nTotal: 200€\n\nIntroduce la tarjeta de crédito para confirmar el pedido o pulsa los botones inferiores para tomar otra decisión");//AÑADE UNA DESCRIPCION             
            dispenser.setOption(0, "Modificar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 0  
            dispenser.setOption(1, "Cancelar pedido");// AÑADE UNA OPCION EN EL MENU PRINCIPAL EN EL BOTON 1 
            respuestaInterfaz = dispenser.waitEvent(waitTime);//ESPERA A QUE EL USARIO INTERACTUE CON EL INTEFAZ Y DEVUELVE LO QUE EL USARIO HA ECHO CON EL INTERFAZ (QUE BOTON SE PULSA, ETC..)      
            
            
            //SE COMPRUBA CUAL HA SIDO LA RESPUESTA DEL INTERFAZ, SI LA RESPUESTA ES '1' INDICA QUE SE HA METIDO UNA TARGETA DE CREDITO
            if (respuestaInterfaz == '1') {//SI HAN METIDO UNA TARJETA DE CREDITO
                dispenser.retainCreditCard(false);//SE RETIENE LA TARJETA PARA HACER LAS COMPROBACIONES

                try {
                    long numeroTajetaIntroducida = dispenser.getCardNumber();//SE CONSULTA AL INTERFAZ QUE NUMERO DE TARGEJETA SE HA INTRODUCIDO
                    System.out.println(numeroTajetaIntroducida);
                    int cantidadAPagar = 200;//SE INVENTA QUE LA CANTIDAD A PAGAR SON 200 
                    boolean ok = bank.doOperation(numeroTajetaIntroducida,cantidadAPagar);//SE PREGUNTA AL BANCO SI ESA TARJETA PUEDE PAGAR ESA CANTIDAD
                    if (ok) {
                        dispenser.setMessageMode();
                        dispenser.setTitle("Proceso de pago exitoso");
                        dispenser.setDescription("Ya puedes recoger tu tarjeta\nTu número de pedido es 33\nRecoge el ticket\nTe rogamos que permanezcas atento a las pantallas");
                        int tiempoEsperaRetiradaTarjeta = 30;
                        dispenser.expelCreditCard(tiempoEsperaRetiradaTarjeta);//SE LIBERA LA TARJETA DURANTE UN TIMPO PARA QUE LA RECOJA EL USAIRO SI NO SE LA TRAGARA
                        
                        //SE CONTRUYE EL MENSAJE QUE SE IMPRIMIRA EN EL TICKET CON EL RESUMEN DEL PEDIDO Y EL PAGO
                        ArrayList <String> ticketText = new ArrayList<>();
                        ticketText.add("Artículos comprados");
                        ticketText.add("=====================");
                        ticketText.add("Menú - 200€");
                        ticketText.add("=====================");
                        ticketText.add("Total: 200€");
                        ticketText.add("");
                        ticketText.add("Número de pedido: 33");
                        dispenser.print(ticketText);//SE GENERA UN TIQUECK CON EL RESULTADO DEL PAGO
                        
                    } else {
                        dispenser.setMessageMode();//SE PONE EL INTERFAZ EN MODO MENSAJE PARA INFORMAR DEL ERROR
                        dispenser.setTitle("Problemas en el proceso de pago");
                        dispenser.setDescription("El banco dice que no tienes dinero. Prueba con otra tarjeta.");            
                        dispenser.expelCreditCard(waitTime);
                    }
                } catch(CommunicationException ex) {//A LA HORA DE PGAR EL SISTEMA BANCARIO PUEDE DAR ERRORES (SIMULA UN SERVIDOR BANCARIO REAL)
                    dispenser.setMessageMode();//SE PONE EL INTERFAZ EN MODO MENSAJE PARA INFORMAR DEL ERROR
                    dispenser.setTitle("Problemas de comunicación en el proceso de pago");
                    dispenser.setDescription("Reintentando");
                }
            }
        }    
    }
}
