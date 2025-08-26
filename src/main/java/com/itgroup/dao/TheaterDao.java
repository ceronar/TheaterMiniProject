package com.itgroup.dao;

import com.itgroup.bean.Theater;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Theater> getAllTheaters() {
        List<Theater> theaterList = new ArrayList<>();
        String sql = "SELECT * FROM THEATER";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                theaterList.add(this.makeBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return theaterList;
    }

    public int updateTheater(Theater upTheater) {
        int cnt = -1;
        String sql ="update THEATER set NAME = ? where THEATER_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, upTheater.getName());
            pstmt.setInt(2, upTheater.getTheaterId());
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

    public int deleteTheater(int theaterId) {
        int cnt = -1;
        String sql = "delete from theater where THEATER_ID = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theaterId);
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
