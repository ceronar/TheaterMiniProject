package com.itgroup.dao;

import com.itgroup.bean.Movie;
import com.itgroup.bean.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScheduleDao extends SuperDao {
    public ScheduleDao() {
        super();
    }

    private Schedule makeBean(ResultSet rs) {
        Schedule bean = null;
        try {
            bean = new Schedule();
            bean.setScheduleId(rs.getInt("SCHEDULE_ID"));
            bean.setMovieId(rs.getInt("MOVIE_ID"));
            bean.setTheaterId(rs.getInt("THEATER_ID"));
            bean.setShowTime(String.valueOf(rs.getDate("SHOW_TIME")));
            bean.setMovieName(rs.getString("MOVIE_NAME"));
            bean.setName(rs.getString("NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<Schedule> getSchedulesByMovieId(int movieId) {
        return null;
    }
}
