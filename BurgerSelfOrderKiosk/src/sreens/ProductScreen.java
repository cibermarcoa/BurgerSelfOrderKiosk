/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sreens;

import manager.Context;
import manager.SimpleKiosk;
import products.IndividualProduct;
import products.MenuCardSection;

/**
 *
 * @author nemo
 */
public class ProductScreen implements KioskScreen {
    private int section;
    
    public ProductScreen(int section) {
        this.section = section;
    }


    @Override
    public KioskScreen show(Context c) {
        SimpleKiosk k = c.getKiosk();
        char res = '\0';
        int i = 0;
        int MAX_SIZE = c.getMenuCard().getSection(this.getSection()).getNumberOfProducts();
        IndividualProduct ip = null;
        
        while (res != 'E' && res != 'F') {
            k.clearScreen();
            ip = c.getMenuCard().getSection(this.getSection()).getProduct(i);
            k.setTitle(ip.getName());
            k.setDescription(ip.getDescription() + "\n" + ip.getPrice() + "$");
            k.setImage("src/" + ip.getImageFileName());
            k.setOption(4, "Añadir al pedido");
            k.setOption(5, "Cancelar añadir");
            if (i > 0)
                k.setOption(6, "&lt;");
            if (i < MAX_SIZE - 1)
                k.setOption(7, "&gt;");
            res = k.waitEvent(60);
            System.out.println(res);
            if (res == 'G')
                i--;
            else if (res == 'H')
                i++;
        }
        if (res == 'E')
            c.getOrder().addProduct(ip);
        return new OrderScreen();
    }
/*
    private void configureScreenButtons() {
        
    }
    public void ProductSelectionScreen(int section) {
        
    }*/
    private int getSection() {
        return this.section;
    }
}
