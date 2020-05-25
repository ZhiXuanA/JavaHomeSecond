package com.xuan.bean;

import java.util.List;

public class CarBean {
    List<CarItem> carObject;

    public List<CarItem> getCarObject() {
        return carObject;
    }

    public void setCarObject(List<CarItem> carObject) {
        this.carObject = carObject;
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "carObject=" + carObject +
                '}';
    }
}
