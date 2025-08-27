package com.itgroup.dao;

import com.itgroup.bean.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends SuperDao {
    public UserDao() {
        super();
    }

    public int addUser(Users users) {
        int cnt = -1;
        String sql = "insert into users(USER_ID, USER_NAME) values(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, users.getUserId());
            pstmt.setString(2, users.getUserName());
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

    public Users getUserById(String userId) {
        Users bean = null; // 찾고자 하는 회원 정보
        String sql = "SELECT * FROM USERS WHERE user_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = new Users();
                bean.setUserId(rs.getString("user_id"));
                bean.setUserName(rs.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return bean;
    }

    public int updateUser(String userId, String name) {
        int cnt = -1;
        String sql ="update users set user_name = ? where user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, userId);
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

    public int deleteUser(String userId) {
        int cnt = -1;
        String sql = "delete from users where user_id = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
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
