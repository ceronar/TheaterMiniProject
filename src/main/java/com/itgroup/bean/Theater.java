package com.itgroup.bean;

public class Theater {
    private int theaterId;      //THEATER_ID	NUMBER
    private String name;        //NAME	        VARCHAR2(50 BYTE)
    private int totalSeats;     //TOTAL_SEATS	NUMBER

    public Theater() {
    }

    public Theater(int theaterId, String name, int totalSeats) {
        this.theaterId = theaterId;
        this.name = name;
        this.totalSeats = totalSeats;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId=" + theaterId +
                ", name='" + name + '\'' +
                ", totalSeats=" + totalSeats +
                '}';
    }
}
