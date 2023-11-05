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
    private int size;
    private int Price;
    private int TotalPrice;
    private int CartID;
    private Products product;
    
    public Cart() {
    }

    public Cart(int AccountID, int ProductID, int Quantity, int size, int Price, int TotalPrice) {
        this.AccountID = AccountID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.size = size;
        this.Price = Price;
        this.TotalPrice = TotalPrice;      
    } 

    public Cart(int AccountID, int ProductID, int Quantity, int size, int Price, int TotalPrice, int CartID) {
        this.AccountID = AccountID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.size = size;
        this.Price = Price;
        this.TotalPrice = TotalPrice;
        this.CartID = CartID;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = getQuantity()*getPrice();
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }
    
    
    @Override
    public String toString() {
        return "Cart{" + "AccountID=" + AccountID + ", ProductID=" + ProductID + ", Quantity=" + Quantity + ", Price=" + Price + ", TotalPrice=" + TotalPrice + '}';
    }

}
