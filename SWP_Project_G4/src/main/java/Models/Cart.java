/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class Cart {

    private int AccountID;
    private int ProductID;
    private int Quantity;
    private int Price;
    private int TotalPrice;

    public Cart() {
    }

    public Cart(int AccountID, int ProductID, int Quantity, int Price, int TotalPrice) {
        this.AccountID = AccountID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.TotalPrice = TotalPrice;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
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

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" + "AccountID=" + AccountID + ", ProductID=" + ProductID + ", Quantity=" + Quantity + ", Price=" + Price + ", TotalPrice=" + TotalPrice + '}';
    }

}
