/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class Brand {

    private String BrandID;
    private String BrandName;
    private String Origin;

    public Brand() {
    }

    public Brand(String BrandID, String BrandName, String Origin) {
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.Origin = Origin;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    @Override
    public String toString() {
        return "Brand{" + "BrandID=" + BrandID + ", BrandName=" + BrandName + ", Origin=" + Origin + '}';
    }

}
