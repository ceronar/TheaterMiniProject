package com.itgroup.dao;

import com.itgroup.bean.Movie;
import com.itgroup.bean.Theater;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Movie getMovieById(int movieId) {
        Movie movie = new Movie();
        String sql = "SELECT * FROM MOVIE WHERE MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                movie = this.makeBean(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        String sql = "SELECT * FROM MOVIE";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                movieList.add(this.makeBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(rs, pstmt, conn);
        }
        return movieList;
    }

    public int updateMovie(Movie upMovie) {
        int cnt = -1;
        String sql ="update MOVIE set TITLE = ?, PRICE = ?, RELEASE_DATE = ? where MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, upMovie.getTitle());
            pstmt.setInt(2, upMovie.getPrice());
            pstmt.setDate(3, Date.valueOf(upMovie.getReleaseDate()));
            pstmt.setInt(4, upMovie.getMovieId());
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

    public int deleteMovie(int movieId) {
        int cnt = -1;
        String sql = "delete from movie where MOVIE_ID = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
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
