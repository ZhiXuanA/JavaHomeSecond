package com.xuan.bean;
import org.json.JSONObject;

public class OrderDetail {
    private String uname;
    private String uemail;
    private  String addre;
    private  String car;
    private  double sum;
    private  String date;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getAddre() {
        return addre;
    }

    public void setAddre(String addre) {
        this.addre = addre;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "uname='" + uname + '\'' +
                ", uemail='" + uemail + '\'' +
                ", addre='" + addre + '\'' +
                ", car='" + car + '\'' +
                ", sum=" + sum +
                ", date='" + date + '\'' +
                '}';
    }
}
