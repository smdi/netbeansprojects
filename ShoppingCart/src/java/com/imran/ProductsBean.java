/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

/**
 *
 * @author Imran
 */
public class ProductsBean implements java.io.Serializable {
    
     String pcode ,pname ;
     int pqty ;
     double pprice ,tprice;

    public double getTprice() {
        return tprice;
    }

    public void setTprice(double tprice) {
        this.tprice = tprice;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPqty() {
        return pqty;
    }

    public void setPqty(int pqty) {
        this.pqty = pqty;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }
    
    
    
}
