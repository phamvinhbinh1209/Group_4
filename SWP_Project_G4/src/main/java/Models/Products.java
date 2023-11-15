/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


/**
 *
 * @author HP
 */
public class Products {

    private int ProductID;
    private String Image;
    private String ProductName;
    private int CategoryID;
    private String BrandID;
    private int Price;
    private String Description;

    public Products() {
    }

    public Products(String Image, String ProductName, int CategoryID, String BrandID, int Price, String Description) {
        this.Image = Image;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.BrandID = BrandID;
        this.Price = Price;
        this.Description = Description;
    }

    public Products(int ProductID, String Image, String ProductName, int CategoryID, String BrandID, int Price, String Description) {
        this.ProductID = ProductID;
        this.Image = Image;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.BrandID = BrandID;
        this.Price = Price;
        this.Description = Description;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Products{" + "ProductID=" + ProductID + ", Image=" + Image + ", ProductName=" + ProductName + ", CategoryID=" + CategoryID + ", BrandID=" + BrandID + ", Price=" + Price + ", Description=" + Description + '}';
    }

}
