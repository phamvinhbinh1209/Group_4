/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Order {
    private int OrderID;
    private Date OrderDate;
    private Date DeliveryDate;
    private boolean Status;
    private String Address;
    private int TotalPrice;
    private int AccountID;

    public Order() {
    }

    public Order(int OrderID, Date OrderDate, Date DeliveryDate, boolean Status, String Address, int TotalPrice, int AccountID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.DeliveryDate = DeliveryDate;
        this.Status = Status;
        this.Address = Address;
        this.TotalPrice = TotalPrice;
        this.AccountID = AccountID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", OrderDate=" + OrderDate + ", DeliveryDate=" + DeliveryDate + ", Status=" + Status + ", Address=" + Address + ", TotalPrice=" + TotalPrice + ", AccountID=" + AccountID + '}';
    }
    
}
