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
    private Translator currentDic = new Translator("Español");
    private Map<String, Translator> dictionaries = new HashMap<>();

    public TranslatorManager() {
        String rutaDirectorioDic = "src/DICCIONARIOS";
        File directorio = new File(rutaDirectorioDic);
        
        
        if (directorio.exists() && directorio.isDirectory()) {
            File[] ficheros = directorio.listFiles();
            for (File fich : ficheros) {
                if (fich.getName().contains(".txt")) {
                    Translator nuevoTraductor = new Translator(fich.getPath());
                    String nombreDelDic = fich.getName().substring(0, fich.getName().length() - 4);
                    dictionaries.put(nombreDelDic, nuevoTraductor);
                    System.out.println(fich.getName());
                }
            }
        }
    }

    public void setCurrentIdiom(String idiom) {
        this.currentDic = this.dictionaries.get(idiom);
    }

    public String translate(String cad) {
        if (this.currentDic != null) {
            return currentDic.translate(cad);
        }
        return cad;
    }

    public List<String> getIdioms() {
        List<String> idioms = new ArrayList<>();
        idioms.addAll(dictionaries.keySet());
        return idioms;
    }
}
