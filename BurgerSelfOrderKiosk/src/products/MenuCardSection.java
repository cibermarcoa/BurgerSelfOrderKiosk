/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;
import java.util.List;
/**
 *
 * @author nemo
 */

public class MenuCardSection {
    private String sectionName;
    private String imageFileName;
    private List<IndividualProduct> productList;
    
    //Constructor
    public MenuCardSection(String name, String imageFileName, List<IndividualProduct> productList) {
        this.sectionName = name;
        this.imageFileName = imageFileName;
        this.productList = productList;
    }
    
    //Obtiene un producto de la lista según el índice
    public IndividualProduct getProduct(int index) {
        if (index < 0 || index > productList.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return productList.get(index);
    }
    
    //Devuelve el nombre de la sección (ej. "Bebidas").
    public String getSectionName() {
        return this.sectionName;
    }
    
    //Devuelve el número de productos en la sección.
    public int getNumberOfProducts() {
        return this.productList.size();
    }
    
    //Devuelve la ruta del archivo de imagen asociada a la sección.
    public String getImageFileName() {
        return this.imageFileName;
    }
}
