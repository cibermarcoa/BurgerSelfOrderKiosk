/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author cibermarcoa
 */
public class Translator {

    private Map<String, String> palabras = new HashMap<>(); // Mapa que almacena las traducciones (clave: texto original, valor: traducción).
    
    //Constructor que carga el archivo de traducciones
    public Translator(String filename) {
        try {
            //Abre el archivo para lectura
            Scanner scannerFile = new Scanner(new File(filename));
            //Leer linea por linea y cargar las traducciones
            while (scannerFile.hasNext()) {
                String line = scannerFile.nextLine();   //Leer linea del archivo
                String[] textTranslation = line.split(":"); // Dividir la línea en clave y valor (separador ":")
                if (textTranslation.length == 2) {  // Verificar que tenga formato "clave:valor"
                    palabras.put(textTranslation[0], textTranslation[1]);   // Añadir la traducción al mapa
                }
            }
            scannerFile.close(); // Cerrar el Scanner
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero de idiomas: " + filename);   //Mensaje si el archivo no se encuentra
        }
    }

    public String translate(String cad) {
        //Verifica si la cadena tiene una traduccion
        if (this.palabras.containsKey(cad)) {
            return palabras.get(cad);   //Devuelve la traduccion
        } else {
            return cad; // Si no existe traducción, devuelve la cadena original
        }
    }
}
