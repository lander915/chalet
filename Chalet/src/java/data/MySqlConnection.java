/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.ChaletException;

/**
 *
 * @author frederic.vlummens
 */
public class MySqlConnection
{
    private static final String URL = "jdbc:mysql://localhost/chalet?useSSL=false";
    private static final String UID = "root";
    private static final String PWD = "";
    
    // loading the class is only required for web applications
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            throw new ChaletException("Unable to load database driver.", ex);
        }
    }
    
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, UID, PWD);
    }
    
}
