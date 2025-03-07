/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.beans.XMLDecoder;
import java.io.File;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author cibermarcoa
 */
public class MenuCard {
    private List<MenuCardSection> sectionList;
    
    //Constructor
    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
    
    //Devuelve la sección del menú según el índice especificado.
    public MenuCardSection getSection(int index) {
        if (index < 0 || index >= sectionList.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return this.sectionList.get(index);
    }
    
    //Devuelve el número total de secciones del menú.
    public int getNumberOfSections() {
        return this.sectionList.size();
    }
    
    //Carga el menú desde un archivo XML.
    public static MenuCard loadFromDisk() {
        String defaultFilePath = "PRODUCTOS/Catalog.xml";  // Se espera que esté en la raíz de la carpeta de clases
        try (InputStream is = MenuCard.class.getClassLoader().getResourceAsStream(defaultFilePath);
             XMLDecoder decoder = new XMLDecoder(is)) {
            if (is == null) {
                throw new RuntimeException("No se encuentra el archivo " + defaultFilePath);
            }
            return (MenuCard) decoder.readObject(); // Deserializa el contenido del archivo XML y lo convierte en un objeto `MenuCard`.
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el menu desde el archivo " + defaultFilePath, e);
        }
    }
}
