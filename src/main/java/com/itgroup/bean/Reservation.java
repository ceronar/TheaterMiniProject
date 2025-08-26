package com.itgroup.bean;

public class Reservation {
    private int reservationId;      //RESERVATION_ID	NUMBER
    private int scheduleId;         //SCHEDULE_ID	    NUMBER
    private String userId;          //USER_ID	        VARCHAR2(50 BYTE)
    private int rowNum;             //ROW_NUM	        NUMBER  행
    private int colNum;             //COL_NUM	        NUMBER  열
    private int price;              //PRICE	            NUMBER(10,0)
    private String reservedAt;      //RESERVED_AT	    TIMESTAMP(6)
    private String title;           //TITLE         MOVIE 영화 이름 join용
    private String name;            //NAME          THEATER 영화관 이름 join용
    private String showTime;        //SHOW_TIME	    SCHEDULE 상영 시간 join용
    private String userName;        //USER_NAME     USERS 예약자명 join용

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
