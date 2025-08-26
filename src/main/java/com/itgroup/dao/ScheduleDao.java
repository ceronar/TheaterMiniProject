package com.itgroup.dao;

import com.itgroup.bean.Schedule;

import java.sql.*;
import java.util.ArrayList;
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
            bean.setShowTime(rs.getString("SHOW_TIME"));
            bean.setTitle(rs.getString("TITLE"));
            bean.setName(rs.getString("NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public int addSchedule(Schedule schedule) {
        int cnt = -1;
        String sql = "insert into SCHEDULE(SCHEDULE_ID, MOVIE_ID, THEATER_ID, SHOW_TIME) values(SCHEDULE_SEQ.NEXTVAL, ?, ?, (TO_DATE(?, 'YYYY-MM-DD HH24:MI')))";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, schedule.getMovieId());
            pstmt.setInt(2, schedule.getTheaterId());
            pstmt.setString(3, schedule.getShowTime());
            cnt = pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            super.close(null, pstmt, conn);
        }
        return cnt;
    }

    public List<Schedule> getSchedulesByMovieId(int movieId) {
        List<Schedule> scheduleList = new ArrayList<>();
        String sql = "SELECT S.SCHEDULE_ID, S.MOVIE_ID, S.THEATER_ID, TO_CHAR(S.SHOW_TIME, 'YYYY-MM-DD HH24:MI') AS SHOW_TIME, M.TITLE, T.NAME FROM SCHEDULE S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID JOIN THEATER T ON S.THEATER_ID = T.THEATER_ID WHERE S.MOVIE_ID = ? ORDER BY S.SHOW_TIME";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                scheduleList.add(this.makeBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return scheduleList;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        String sql = "SELECT S.SCHEDULE_ID, S.MOVIE_ID, S.THEATER_ID, TO_CHAR(S.SHOW_TIME, 'YYYY-MM-DD HH24:MI') AS SHOW_TIME, M.TITLE, T.NAME FROM SCHEDULE S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID JOIN THEATER T ON S.THEATER_ID = T.THEATER_ID ORDER BY S.SCHEDULE_ID DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                scheduleList.add(this.makeBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return scheduleList;
    }

    public int updateSchedule(Schedule schedule) {
        int cnt = -1;
        String sql ="update SCHEDULE set SHOW_TIME = (TO_DATE(?, 'YYYY-MM-DD HH24:MI') where SCHEDULE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, schedule.getShowTime());
            pstmt.setInt(2, schedule.getScheduleId());
            cnt = pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            super.close(null, pstmt, conn);
        }
        return cnt;
    }

    public int deleteSchedule(int scheduleId) {
        int cnt = -1;
        String sql = "delete from SCHEDULE where SCHEDULE_ID = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, scheduleId);
            cnt = pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            super.close(null, pstmt, conn);
        }
        return cnt;
    }
}
