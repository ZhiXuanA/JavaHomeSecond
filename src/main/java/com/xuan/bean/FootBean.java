package com.xuan.bean;

public class FootBean {
    String uname;
    String pno;
    int time;

    @Override
    public String toString() {
        return "FootBean{" +
                "uname='" + uname + '\'' +
                ", pno='" + pno + '\'' +
                ", time=" + time +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
