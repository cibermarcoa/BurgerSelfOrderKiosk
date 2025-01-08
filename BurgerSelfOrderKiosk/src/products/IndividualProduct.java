/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

/**
 *
 * @author cibermarcoa
 */
public class IndividualProduct implements Product{
    private String name;
    private String description;
    private String imageFileName;
    private int price;

    // Constructor
    public IndividualProduct(String name, String description, String imageFileName, int price) {
        this.name = name;
        this.description = description;
        this.imageFileName = imageFileName;
        this.price = price;
    }

    @Override
    public int getPrice() { //Obtiene el precio del producto
        return this.price;
    }

    @Override
    public String getName() {   //Obtiene el nombre del producto
        return this.name;
    }

    public String getDescription() {    //Obtiene la descripción del producto
        return this.description;
    }

    public String getImageFileName() {  //Obtiene el nombre del archivo de la imagen del producto.
        return this.imageFileName;
    }
}