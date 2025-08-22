package com.itgroup.bean;

public class Reservation {
    private int reservationId;    //RESERVATION_ID	NUMBER
    private int scheduleId;    //SCHEDULE_ID	NUMBER
    private String userId;    //USER_ID	VARCHAR2(50 BYTE)
    private int rowNum;    //ROW_NUM	NUMBER
    private int colNum;    //COL_NUM	NUMBER
    private int price;    //PRICE	NUMBER(10,0)
    private String reservedAt;    //RESERVED_AT	TIMESTAMP(6)

    public Reservation() {
    }

    public Reservation(int reservationId, int scheduleId, String userId, int rowNum, int colNum, int price, String reservedAt) {
        this.reservationId = reservationId;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.price = price;
        this.reservedAt = reservedAt;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(String reservedAt) {
        this.reservedAt = reservedAt;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", scheduleId=" + scheduleId +
                ", userId='" + userId + '\'' +
                ", rowNum=" + rowNum +
                ", colNum=" + colNum +
                ", price=" + price +
                ", reservedAt='" + reservedAt + '\'' +
                '}';
    }
}
