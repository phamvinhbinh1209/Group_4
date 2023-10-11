/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class Size {

    private int SizeID;
    private String NumSize;

    public Size() {
    }

    public Size(int SizeID, String NumSize) {
        this.SizeID = SizeID;
        this.NumSize = NumSize;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public String getNumSize() {
        return NumSize;
    }

    public void setNumSize(String NumSize) {
        this.NumSize = NumSize;
    }
}
