package com.itgroup.dao;

import com.itgroup.bean.Movie;

import java.sql.*;

public class MovieDao extends SuperDao {
    public MovieDao() {
        super();
    }

    private Movie makeBean(ResultSet rs) {
        Movie bean = null;
        try {
            bean = new Movie();
            bean.setMovieId(rs.getInt("MOVIE_ID"));
            bean.setTitle(rs.getString("TITLE"));
            bean.setPrice(rs.getInt("PRICE"));
            bean.setReleaseDate(String.valueOf(rs.getDate("RELEASE_DATE")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public int addMovie(Movie movie) {
        int cnt = -1;
        String sql = "insert into movie(MOVIE_ID, TITLE, PRICE, RELEASE_DATE) values(MOVIE_SEQ.NEXTVAL, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getPrice());
            pstmt.setDate(3, Date.valueOf(movie.getReleaseDate()));
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
