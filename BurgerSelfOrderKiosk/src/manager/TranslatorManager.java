/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslatorManager {
    private Translator currentDic = new Translator("Español");  // Diccionario de traducción actual (por defecto "Español").
    private Map<String, Translator> dictionaries = new HashMap<>(); // Mapa de diccionarios disponibles (clave: nombre del idioma).

    public TranslatorManager() {
        String rutaDirectorioDic = "src/DICCIONARIOS";  // Ruta donde se encuentran los archivos de diccionarios.
        File directorio = new File(rutaDirectorioDic);
        
        // Comprobar si el directorio existe y contiene archivos de diccionarios.
        if (directorio.exists() && directorio.isDirectory()) {
            File[] ficheros = directorio.listFiles();
            for (File fich : ficheros) {
                if (fich.getName().contains(".txt")) {// Filtrar solo archivos con extensión ".txt".  
                    Translator nuevoTraductor = new Translator(fich.getPath()); // Crear un nuevo traductor para el archivo.
                    String nombreDelDic = fich.getName().substring(0, fich.getName().length() - 4); // Obtener el nombre del diccionario (sin ".txt").
                    dictionaries.put(nombreDelDic, nuevoTraductor); // Añadir el diccionario al mapa.
                    System.out.println(fich.getName()); // Mostrar el nombre del diccionario cargado en la consola.
                }
            }
        }
    }

    public void setCurrentIdiom(String idiom) {
        this.currentDic = this.dictionaries.get(idiom); // Cambiar el diccionario actual al seleccionado.
    }
    
    //Traduce una cadena de texto usando el diccionario actual.
    public String translate(String cad) {
        if (this.currentDic != null) {
            return currentDic.translate(cad);
        }
        return cad;
    }
    
    //Devuelve una lista de los idiomas disponibles.
    public List<String> getIdioms() {
        List<String> idioms = new ArrayList<>();    //Crea una lista vacia.
        idioms.addAll(dictionaries.keySet());   // Añadir los nombres de los diccionarios al listado.
        return idioms;
    }
}
