/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class OrderDetail {

    private int OrderItemID;
    private int Quantity;
    private int Price;
    private int ProductID;
    private int OrderID;
    private int Size;
  

    public OrderDetail() {
    }

    public OrderDetail(int OrderItemID, int Quantity, int Price, int ProductID, int OrderID, int Size) {
        this.OrderItemID = OrderItemID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.ProductID = ProductID;
        this.OrderID = OrderID;
        this.Size = Size;
    }

    public int getOrderItemID() {
        return OrderItemID;
    }

    public void setOrderItemID(int OrderItemID) {
        this.OrderItemID = OrderItemID;
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

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "OrderItemID=" + OrderItemID + ", Quantity=" + Quantity + ", Price=" + Price + ", ProductID=" + ProductID + ", OrderID=" + OrderID + ", Size=" + Size + '}';
    }

   
}
