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
public class Product {
    
    public int id;
    public String naam;
    public int aantal;
    public double prijs;
    public String imageUrl;
    public int verbruik;

    public Product(int id, String naam, int aantal, double prijs, String imgUrl, int verbruik) {
        this.id = id;
        this.naam = naam;
        this.aantal = aantal;
        this.prijs = prijs;
        this.imageUrl = "Media/"+imgUrl;
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

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imgUrl) {
        this.imageUrl = "Media/"+imgUrl;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", naam=" + naam + ", aantal=" + aantal + ", prijs=" + prijs + ", imgUrl=" + imageUrl + '}';
    }

    
    
}
