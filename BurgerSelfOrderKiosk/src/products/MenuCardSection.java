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

    public MenuCardSection(String name, String imageFileName, List<IndividualProduct> productList) {
        this.sectionName = name;
        this.imageFileName = imageFileName;
        this.productList = productList;
    }

    public IndividualProduct getProduct(int index) {
        if (index < 0 || index > productList.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return productList.get(index);
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public int getNumberOfProducts() {
        return this.productList.size();
    }

    public String getImageFileName() {
        return this.imageFileName;
    }
}
