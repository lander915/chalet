/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Product;
import java.util.List;

/**
 *
 * @author lander
 */
public interface InventarisRepository {
    
    public List<Product> getAllProducts();
    public Product getProduct(int id);
    public void updateProduct(int id, int aantal);
    public void deleteProduct(int id);
    public void refillProduct(int id, int aantal);
    public void addProduct(String naam, double prijs, int aantal);
}
