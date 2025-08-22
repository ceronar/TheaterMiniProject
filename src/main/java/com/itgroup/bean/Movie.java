package com.itgroup.bean;

public class Movie {
    private int movieId;            //MOVIE_ID	    NUMBER
    private String title;           //TITLE	        VARCHAR2(100 BYTE)
    private int price;              //PRICE	        NUMBER(10,0)
    private String releaseDate;     //RELEASE_DATE	DATE

    public Movie() {
    }

    public Movie(int movieId, String title, int price, String releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
