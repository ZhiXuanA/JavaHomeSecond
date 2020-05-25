package com.xuan.bean;

public class CarItem {
    private int pno;
    private long nums;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public long getNums() {
        return nums;
    }

    public void setNums(long nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "CarItem{" +
                "pno=" + pno +
                ", nums=" + nums +
                '}';
    }
}
