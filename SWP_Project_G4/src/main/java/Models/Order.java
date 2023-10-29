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
    private int ID;
    private Date Date;
    private String Address;
    private String Phone;
    private String Product;
    private int Price;
    private Date DeliveryDate;
    private String Status;

    public Order() {
    }

    public Order(int ID, Date Date, String Address, String Phone, String Product, int Price, Date DeliveryDate, String Status) {
        this.ID = ID;
        this.Date = Date;
        this.Address = Address;
        this.Phone = Phone;
        this.Product = Product;
        this.Price = Price;
        this.DeliveryDate = DeliveryDate;
        this.Status = Status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
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

    @Override
    public String toString() {
        return "Order{" + "ID=" + ID + ", Date=" + Date + ", Address=" + Address + ", Phone=" + Phone + ", Product=" + Product + ", Price=" + Price + ", DeliveryDate=" + DeliveryDate + ", Status=" + Status + '}';
    }


}
