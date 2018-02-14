/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Lid;
import java.sql.Blob;
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
public class MySqlLedenRepository implements LedenRepository{

    private static final String SQL_SELECT_ALL_MEMBERS = "select * from leden order by verbruik desc";
    private static final String SQL_SELECT_MEMBER_BY_ID = "select * from leden where id = ?";
    private static final String SQL_UPDATE_MEMBER = "UPDATE leden SET naam= ?, geld = geld-?, verbruik = verbruik+? WHERE id = ?";
    private static final String SQL_ADD_MEMBER = "INSERT INTO leden (naam, geld, img) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_PICTURE = "UPDATE leden set img = ? WHERE id = ?";
    
    @Override
    public List<Lid> getAllMembers() {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_ALL_MEMBERS);
            ResultSet rs = prep.executeQuery())
        {
            List<Lid> leden = new ArrayList<>();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String naam = rs.getString("naam");
                double geld = rs.getDouble("geld");
                String img = rs.getString("img");
                double verbruik = rs.getDouble("verbruik");
                
                Lid l = new Lid(id, naam, geld, img, verbruik);
                
                leden.add(l);
            }
            
            return leden;
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to get members from database.", ex);
        }
    }

    @Override
    public Lid getMember(int id) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_MEMBER_BY_ID))
        {
            prep.setInt(1, id);
            
            try(ResultSet rs = prep.executeQuery())
            {
                Lid l = null;

                if(rs.next())
                {
                    int LidId = rs.getInt("id");
                    String naam = rs.getString("naam");
                    double geld = rs.getDouble("geld");
                    String img = rs.getString("img");
                    double verbruik = rs.getDouble("verbruik");

                    l = new Lid(LidId, naam, geld, img, verbruik);
                }

                return l;
            }
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to get member from database.", ex);
        }
    }

    @Override
    public void updateMember(int id, String naam, double geld, double verbruik) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_MEMBER))
        {
            prep.setString(1, naam);
            prep.setDouble(2, geld);
            prep.setDouble(3, verbruik);
            prep.setInt(4, id);
            
            prep.executeUpdate();
            prep.close();
            
        }
        catch(SQLException ex)
        {
            throw new ChaletException("Unable to update member to database.", ex);
        }
    }
    

    @Override
    public void addMember(String naam, double geld) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_MEMBER, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setString(1, naam);
            prep.setDouble(2, geld);
            String imageUrl = "default.png";
            prep.setString(3, imageUrl);
            
            prep.executeUpdate();
            
        }
        catch (SQLException ex)
        {
            throw new ChaletException("Unable to add member to database.", ex);
        }
    }

    public void updatePicture(int id, Blob blob) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_PICTURE, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setBlob(1, blob);
            prep.setDouble(2, id);
            
            prep.executeUpdate();
            
        }
        catch (SQLException ex)
        {
            throw new ChaletException("Unable to add picture to database.", ex);
        }
    }
    
}
