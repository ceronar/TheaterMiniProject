package com.itgroup.dao;

import com.itgroup.bean.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao extends SuperDao {
    public ReservationDao() {
        super();
    }

    private Reservation makeBean(ResultSet rs) {
        Reservation bean = null;
        try {
            bean = new Reservation();
            bean.setReservationId(rs.getInt("RESERVATION_ID"));
            bean.setScheduleId(rs.getInt("SCHEDULE_ID"));
            bean.setUserId(rs.getString("USER_ID"));
            bean.setRowNum(rs.getInt("ROW_NUM"));
            bean.setColNum(rs.getInt("COL_NUM"));
            bean.setPrice(rs.getInt("PRICE"));
            bean.setReservedAt(String.valueOf(rs.getTimestamp("RESERVED_AT")));
            bean.setTitle(rs.getString("TITLE"));
            bean.setName(rs.getString("NAME"));
            bean.setShowTime(String.valueOf(rs.getDate("SHOW_TIME")));
            bean.setUserName(rs.getString("USER_NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public int addReservation(Reservation reservation) {
        int cnt = -1;
        String sql = "insert into RESERVATION(RESERVATION_ID, SCHEDULE_ID, USER_ID, ROW_NUM, COL_NUM, PRICE, RESERVED_AT) values(RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservation.getScheduleId());
            pstmt.setString(2, reservation.getUserId());
            pstmt.setInt(3, reservation.getRowNum());
            pstmt.setInt(4, reservation.getColNum());
            pstmt.setInt(5, reservation.getPrice());
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

    public Reservation getReservationById(int reservationId) {

        return null;
    }

    public List<Reservation> getReservationsByUser(String userId) {
        List<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT R.RESERVATION_ID, R.SCHEDULE_ID, R.USER_ID, R.ROW_NUM, R.COL_NUM, R.PRICE, R.RESERVED_AT, M.TITLE, T.NAME, S.SHOW_TIME, U.USER_NAME" +
                " FROM RESERVATION R" +
                " JOIN SCHEDULE S ON R.SCHEDULE_ID = S.SCHEDULE_ID" +
                " JOIN THEATER T ON S.THEATER_ID = T.THEATER_ID" +
                " JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID" +
                " JOIN USERS U ON R.USER_ID = U.USER_ID" +
                " WHERE U.USER_ID = ?" +
                " ORDER BY R.ROW_NUM, R.COL_NUM";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                reservationList.add(this.makeBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return reservationList;
    }

    public List<Reservation> getReservationsBySchedule(int scheduleId) {
        List<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT R.RESERVATION_ID, R.SCHEDULE_ID, R.USER_ID, R.ROW_NUM, R.COL_NUM, R.PRICE, R.RESERVED_AT, M.TITLE, T.NAME, S.SHOW_TIME, U.USER_NAME" +
                " FROM RESERVATION R" +
                " JOIN SCHEDULE S ON R.SCHEDULE_ID = S.SCHEDULE_ID" +
                " JOIN THEATER T ON S.THEATER_ID = T.THEATER_ID" +
                " JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID" +
                " JOIN USERS U ON R.USER_ID = U.USER_ID" +
                " WHERE R.SCHEDULE_ID = ?" +
                " ORDER BY R.ROW_NUM, R.COL_NUM";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, scheduleId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                reservationList.add(this.makeBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return reservationList;
    }

    public int deleteReservation(int reservationId) {
        int cnt = -1;
        String sql = "delete from RESERVATETION where SCHEDULE_ID = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
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
