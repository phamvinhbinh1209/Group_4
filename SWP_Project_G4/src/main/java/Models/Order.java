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
    private Date Date;
    private String Address;
    private String Phone;
    private int TotalPrice;
    private Date DeliveryDate;
    private String Status;
    private int AccountID;

    public Order() {
    }

    public Order(int OrderID, Date Date, String Address, String Phone, int TotalPrice, Date DeliveryDate, String Status, int AccountID) {
        this.OrderID = OrderID;
        this.Date = Date;
        this.Address = Address;
        this.Phone = Phone;
        this.TotalPrice = TotalPrice;
        this.DeliveryDate = DeliveryDate;
        this.Status = Status;
        this.AccountID = AccountID;
    }

    public Order(int OrderID, Date Date, String Address, String Phone, int TotalPrice, Date DeliveryDate, String Status) {
        this.OrderID = OrderID;
        this.Date = Date;
        this.Address = Address;
        this.Phone = Phone;
        this.TotalPrice = TotalPrice;
        this.DeliveryDate = DeliveryDate;
        this.Status = Status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", Date=" + Date + ", Address=" + Address + ", Phone=" + Phone + ", TotalPrice=" + TotalPrice + ", DeliveryDate=" + DeliveryDate + ", Status=" + Status + ", AccountID=" + AccountID + '}';
    }

}
