package com.itgroup;

import com.itgroup.bean.*;
import com.itgroup.dao.*;

import java.util.Scanner;
import java.util.regex.Pattern;

public class DataManager {
    private MovieDao mDao = null;
    private UserDao uDao = null;
    private TheaterDao tDao = null;
    private ScheduleDao sDao = null;
    private ReservationDao rDao = null;

    public DataManager() {
        this.mDao = new MovieDao();
        this.uDao = new UserDao();
        this.tDao = new TheaterDao();
        this.sDao = new ScheduleDao();
        this.rDao = new ReservationDao();
    }

    // MovieDAO
    public void addMovie(Scanner sc) {
        int cnt = -1;
        Movie movie = new Movie();
        System.out.print("영화 제목 : ");
        movie.setTitle(sc.nextLine());
        System.out.print("가격 : ");
        movie.setPrice(Integer.parseInt(sc.nextLine()));
        String date = "";
        while (true) {
            System.out.print("개봉일(YYYY-MM-DD) : ");
            date = sc.nextLine();
            String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
            if (Pattern.matches(regex, date)) {
                break;
            } else {
                System.out.println("유효하지 않은 날짜 형식입니다.");
            }
        }
        movie.setReleaseDate(date);
        cnt = mDao.addMovie(movie);
        if(cnt > 0) {
            System.out.println("영화 추가 성공");
        } else {
            System.out.println("영화 추가 실패");
        }
    }

    public void getMovieById(int movieId) {

    }

    public void getAllMovies() {

    }

    public void updateMovie(Movie movie) {

    }
    public void deleteMovie(int movieId) {

    }

    // UserDAO
    public Users login(Scanner sc) {
        System.out.print("ID : ");
        String id = sc.nextLine();
        Users loginUser = uDao.getUserById(id);
        if (loginUser != null) {
            System.out.println("로그인 성공");
            return loginUser;
        } else {
            System.out.println("로그인 실패");
            return null;
        }
    }

    public Users signUp(Scanner sc) {
        String id = "";
        while (true) {
            System.out.print("사용할 ID : ");
            id = sc.nextLine();
            if(uDao.getUserById(id) == null) {
                break;
            } else {
                System.out.println("이미 존재하는 아이디 입니다.");
            }
        }
        System.out.print("이름 : ");
        String name = sc.nextLine();
        int result = uDao.addUser(new Users(id, name));
        if(result > 0) {
            System.out.println("가입 성공");
            return uDao.getUserById(id);
        } else {
            System.out.println("가입 실패");
            return null;
        }
    }

    public Users updateUser(Scanner sc, Users loginUser) {
        System.out.print("변경할 이름 : ");
        String name = sc.nextLine();
        int result = uDao.updateUser(loginUser.getUserId(), name);
        if(result > 0) {
            System.out.println("회원 정보 변경 성공");
            return uDao.getUserById(loginUser.getUserId());
        } else {
            System.out.println("회원 정보 변경 실패");
            return loginUser;
        }
    }

    public Users withdraw(Scanner sc, Users loginUser) {
        System.out.print("탈퇴할 아이디 : ");
        String id = sc.nextLine();
        int cnt = -1;
        if (loginUser.getUserId().equals(id)) {
            cnt = uDao.deleteUser(id);
            if (cnt > 0) {
                System.out.println("탈퇴 성공");
                return null;
            } else {
                System.out.println("탈퇴 실패");
                return loginUser;
            }
        } else {
            System.out.println("현재 접속한 아이디와 다릅니다.");
            return loginUser;
        }
    }

    public Users userInfo(Scanner sc, Users loginUser) {
        System.out.println("내 정보");
        System.out.println("아이디 : " + loginUser.getUserId());
        System.out.println("이름 : " + loginUser.getUserName());
        System.out.println("0.돌아가기, 1. 내정보수정, 2.탈퇴");
        int menu = Integer.parseInt(sc.nextLine());
        switch (menu) {
            case 0:
                break;
            case 1:
                loginUser = this.updateUser(sc, loginUser);
                break;
            case 2:
                loginUser = this.withdraw(sc, loginUser);
                break;
            default:
        }
        return loginUser;
    }

    // TheaterDAO
    public void addTheater(Theater theater) {

    }

    public void getTheaterById(int theaterId) {

    }

    public void getAllTheaters() {

    }

    // ScheduleDAO
    public void addSchedule(Schedule schedule) {

    }

    public void getScheduleById(int scheduleId) {

    }

    public void getSchedulesByMovie(int movieId) {

    }

    public void getSchedulesByTheater(int theaterId) {

    }

    public void getAllSchedules() {

    }

    // ReservationDAO
    public void addReservation(Reservation reservation) {

    }

    public void getReservationById(int reservationId) {

    }

    public void getReservationsByUser(String userId) {

    }

    public void getReservationsBySchedule(int scheduleId) {

    }

    public void deleteReservation(int reservationId) {

    }
}
