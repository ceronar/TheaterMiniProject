package com.itgroup.dao;

import com.itgroup.bean.Theater;

import java.sql.*;

public class TheaterDao extends SuperDao {
    public TheaterDao() {
        super();
    }

    private Theater makeBean(ResultSet rs) {
        Theater bean = null;
        try {
            bean = new Theater();
            bean.setTheaterId(rs.getInt("THEATER_ID"));
            bean.setName(rs.getString("NAME"));
            bean.setTotalSeats(rs.getInt("TOTAL_SEATS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public int addTheater(Theater theater) {
        int cnt = -1;
        String sql = "insert into THEATER(THEATER_ID, NAME, TOTAL_SEATS) values(THEATER_SEQ.NEXTVAL, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, theater.getName());
            pstmt.setInt(2, theater.getTotalSeats());
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

    public Theater getTheaterById(int theaterId) {
        Theater bean = null;
        String sql = "SELECT * FROM THEATER WHERE THEATER_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theaterId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = this.makeBean(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return bean;
    }
}
