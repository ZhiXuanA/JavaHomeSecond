package com.xuan.bean;

public class Product {
    private int pno;
    private String pname;
    private String pnote;
    private double pprice;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPnote() {
        return pnote;
    }

    public void setPnote(String pnote) {
        this.pnote = pnote;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pno=" + pno +
                ", pname='" + pname + '\'' +
                ", pnote='" + pnote + '\'' +
                ", pprice=" + pprice +
                '}';
    }
}
