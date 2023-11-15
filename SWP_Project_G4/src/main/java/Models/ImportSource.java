/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Hung
 */
public class ImportSource {

    private int SizeID;
    private int NumSize;
    private int ProductID;
    private int Quantity;

    public ImportSource() {
    }

    public ImportSource(int SizeID, int NumSize, int ProductID, int Quantity) {
        this.SizeID = SizeID;
        this.NumSize = NumSize;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public ImportSource(int NumSize, int ProductID, int Quantity) {
        this.NumSize = NumSize;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }

    public int getNumSize() {
        return NumSize;
    }

    public void setNumSize(int NumSize) {
        this.NumSize = NumSize;
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

}
