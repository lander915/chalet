/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ChaletException;

/**
 *
 * @author lander
 */
public class MySqlInventarisRepository implements InventarisRepository{

    private static final String SQL_SELECT_ALL_PRODUCTS = "select * from inventaris order by verbruik desc";
    private static final String SQL_SELECT_PRODUCT_BY_ID = "select * from inventaris where id = ?";
    private static final String SQL_UPDATE_AANTAL = "UPDATE inventaris SET aantal = aantal - ?, verbruik = verbruik + ? WHERE id = ?";
    private static final String SQL_REFILL_PRODUCT = "UPDATE inventaris set aantal = aantal + ? WHERE id = ?";
    private static final String SQL_ADD_PRODUCT = "INSERT INTO inventaris (naam, prijs, aantal, img) VALUES (?, ?, ?, ?);";
    
    @Override
    public List<Product> getAllProducts() {
       try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_ALL_PRODUCTS);
            ResultSet rs = prep.executeQuery())
        {
            List<Product> products = new ArrayList<>();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String naam = rs.getString("naam");
                double prijs = rs.getDouble("prijs");
                int aantal = rs.getInt("aantal");
                String img = rs.getString("img");
                int verbruik = rs.getInt("verbruik");
                
                Product p = new Product(id, naam, aantal, prijs, img, verbruik);
                
                products.add(p);
            }
            
            return products;
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to get products from database.", ex);
        }
    }

    @Override
    public Product getProduct(int id) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_PRODUCT_BY_ID))
        {
            prep.setInt(1, id);
            
            try(ResultSet rs = prep.executeQuery())
            {
               Product p = null;

                if(rs.next())
                {
                    int prodId = rs.getInt("id");
                    String naam = rs.getString("naam");
                    int aantal = rs.getInt("aantal");
                    double prijs = rs.getDouble("prijs");
                    String img = rs.getString("img");
                    int verbruik = rs.getInt("verbruik");

                    p = new Product(prodId, naam, aantal, prijs, img, verbruik);
                }

                return p;
            }
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to get product from database.", ex);
        }
    }

    
    @Override
    public void updateProduct(int id, int aantal) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_AANTAL))
        {
            prep.setInt(1, aantal);
            prep.setInt(2, aantal);
            prep.setInt(3, id);
            
            prep.executeUpdate();
            prep.close();
            
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to get product from database.", ex);
        }
    }
    
    @Override
    public void deleteProduct(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refillProduct(int id, int aantal) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_REFILL_PRODUCT))
        {
            prep.setInt(1, aantal);
            prep.setInt(2, id);
            
            prep.executeUpdate();
            prep.close();
            
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to get product from database.", ex);
        }
    }

    @Override
    public void addProduct(String naam, double prijs, int aantal, String imageUrl) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_PRODUCT))
        {
            //naam prijs aantal img
            prep.setString(1, naam);
            prep.setDouble(2, prijs);
            prep.setInt(3, aantal);
            prep.setString(4, imageUrl);
            
            prep.executeUpdate();
            prep.close();
            
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to add product to database.", ex);
        }
    }
}
