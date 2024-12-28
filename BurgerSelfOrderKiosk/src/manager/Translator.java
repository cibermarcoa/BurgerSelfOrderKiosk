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

    private Map<String, String> palabras = new HashMap<>();

    public Translator(String filename) {
        try {
            Scanner scannerFile = new Scanner(new File(filename));
            while (scannerFile.hasNext()) {
                String line = scannerFile.nextLine();
                String[] textTranslation = line.split(":");
                if (textTranslation.length == 2) {
                    palabras.put(textTranslation[0], textTranslation[1]);
                }
            }
            scannerFile.close(); // Asegúrate de cerrar el Scanner
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero de idiomas: " + filename);
        }
    }

    public String translate(String cad) {
        if (this.palabras.containsKey(cad)) {
            return palabras.get(cad);
        } else {
            return cad;
        }
    }
}
