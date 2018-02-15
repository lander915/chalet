/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Lid;
import java.sql.Blob;
import java.util.List;

/**
 *
 * @author lander
 */
public interface LedenRepository {
    
    public List<Lid> getAllMembers();
    public Lid getMember(int id);
    public void updateMember(int id, String naam, double geld, double verbruik);
    public void addMember(String naam, double geld);
}
