package com.example.lodgebilling;

public class Customer {

    private String name;
    private String province;
    private String roomType;
    private int nights;
    private double bill;

    public Customer(String name, String province,
                    String roomType, int nights, double bill) {

        this.name = name;
        this.province = province;
        this.roomType = roomType;
        this.nights = nights;
        this.bill = bill;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public double getBill() {
        return bill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }
}