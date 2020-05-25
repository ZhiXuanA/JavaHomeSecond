package com.xuan.bean;

public class OderItem {
    private Product product;
    private int num;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OderItem{" +
                "product=" + product +
                ", num=" + num +
                '}';
    }
}
