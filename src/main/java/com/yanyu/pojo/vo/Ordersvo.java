package com.yanyu.pojo.vo;

import java.util.Date;

public class Ordersvo {
    private Integer oId;

    private Integer pNum;

    private Double money;

    private String oName;

    private String oAddress;

    private String phone;

    private String pName;

    private int paystate;

    public Ordersvo() {
    }

    @Override
    public String toString() {
        return "Ordersvo{" +
                "oId=" + oId +
                ", pNum=" + pNum +
                ", money=" + money +
                ", oName='" + oName + '\'' +
                ", oAddress='" + oAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", pName='" + pName + '\'' +
                ", paystate=" + paystate +
                '}';
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public Ordersvo(Integer oId, Integer pNum, Double money, String oName, String oAddress, String phone, String pName, int paystate) {
        this.oId = oId;
        this.pNum = pNum;
        this.money = money;
        this.oName = oName;
        this.oAddress = oAddress;
        this.phone = phone;
        this.pName = pName;
        this.paystate = paystate;
    }
}
