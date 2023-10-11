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
    private int SizeID;

    public OrderDetail() {
    }
    private int ColorID;

    public OrderDetail(int OrderItemID, int Quantity, int Price, int ProductID, int OrderID, int SizeID, int ColorID) {
        this.OrderItemID = OrderItemID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.ProductID = ProductID;
        this.OrderID = OrderID;
        this.SizeID = SizeID;
        this.ColorID = ColorID;
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

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getColorID() {
        return ColorID;
    }

    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "OrderItemID=" + OrderItemID + ", Quantity=" + Quantity + ", Price=" + Price + ", ProductID=" + ProductID + ", OrderID=" + OrderID + ", SizeID=" + SizeID + ", ColorID=" + ColorID + '}';
    }

}
