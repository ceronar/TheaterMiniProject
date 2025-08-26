package com.itgroup.bean;

public class Schedule {
    private int scheduleId;     //SCHEDULE_ID	NUMBER
    private int movieId;        //MOVIE_ID	    NUMBER
    private int theaterId;      //THEATER_ID	NUMBER
    private String showTime;    //SHOW_TIME	    DATE
    private String title;       //TITLE 영화 이름 join용
    private String name;        //NAME 영화관 이름 join용

    public Schedule() {
    }

    public Schedule(int scheduleId, int movieId, int theaterId, String showTime) {
        this.scheduleId = scheduleId;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.showTime = showTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
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

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", movieId=" + movieId +
                ", theaterId=" + theaterId +
                ", showTime='" + showTime + '\'' +
                '}';
    }
}

