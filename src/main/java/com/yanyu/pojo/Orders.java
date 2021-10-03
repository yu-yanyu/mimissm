package com.yanyu.pojo;

import java.util.Date;

public class Orders {
    private Integer oId;

    private Integer pId;

    private Integer pNum;

    private Double money;

    private String oName;

    private String oAddress;

    private String phone;

    private Integer paystate;

    private Date ordertime;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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
        this.oName = oName == null ? null : oName.trim();
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress == null ? null : oAddress.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getPaystate() {
        return paystate;
    }

    public void setPaystate(Integer paystate) {
        this.paystate = paystate;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "oId=" + oId +
                ", pId=" + pId +
                ", pNum=" + pNum +
                ", money=" + money +
                ", oName='" + oName + '\'' +
                ", oAddress='" + oAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", paystate=" + paystate +
                ", ordertime=" + ordertime +
                '}';
    }
}