/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author lander
 */
public class Lid {
    
    public int id;
    public String naam;
    public double geld;
    public String imageUrl;
    public double verbruik;

    public Lid(int id, String naam, double geld, String imageUrl, double verbruik) {
        this.id = id;
        this.naam = naam;
        this.geld = geld;
        this.imageUrl = imageUrl;
        this.verbruik = verbruik;
    }

    public double getVerbruik() {
        return verbruik;
    }

    public void setVerbruik(double verbruik) {
        this.verbruik = verbruik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getGeld() {
        return geld;
    }

    public void setGeld(double geld) {
        this.geld = geld;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Lid{" + "id=" + id + ", naam=" + naam + ", geld=" + geld + ", imageUrl=" + imageUrl + '}';
    }
    
    
}
