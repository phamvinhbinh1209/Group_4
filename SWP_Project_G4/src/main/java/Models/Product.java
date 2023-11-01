/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Hung
 */
public class Product {

    private int ID;
    private String ProductName;
    private String Brand;
    private String Color;
    private int Size;
    private int Quantity;
    private int Price;
    private String Picture;

    public Product() {
    }

    public Product(int ID, String ProductName, String Brand, String Color, int Size, int Quantity, int Price, String Picture) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.Brand = Brand;
        this.Color = Color;
        this.Size = Size;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Picture = Picture;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    @Override
    public String toString() {
        return "AdminHome{" + "ID=" + ID + ", ProductName=" + ProductName + ", Brand=" + Brand + ", Color=" + Color + ", Size=" + Size + ", Quantity=" + Quantity + ", Price=" + Price + '}';
    }
}
