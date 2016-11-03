
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author a5-jama
 */
public class Payment {

    private int id;
    private String memID;
    private char typeOfPayment;
    private float amount;
    private Date date;

    public Payments() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public char getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(char typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getMemID() {
        return memID;
    }

    public void setMemID(String memID) {
        this.memID = memID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
