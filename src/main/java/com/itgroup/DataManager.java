package com.itgroup;

import com.itgroup.bean.*;
import com.itgroup.dao.*;

import java.util.*;
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

    public void getAllMovies() {
        List<Movie> movieList = mDao.getAllMovies();
        if (movieList != null) {
            System.out.printf("%-5s %-20s %-8s %-12s%n", "번호", "제목", "가격", "개봉일");
            for (Movie bean : movieList) {
                System.out.printf("%-5d %-20s %-8d %-12s%n",
                        bean.getMovieId(),
                        bean.getTitle(),
                        bean.getPrice(),
                        bean.getReleaseDate());
            }
        } else {
            System.out.println("개봉중인 영화가 없습니다.");
        }
    }

    public void updateMovie(Scanner sc) {
        int cnt = -1;
        Movie upMovie = new Movie();
        System.out.print("영화 번호 : ");
        upMovie.setMovieId(Integer.parseInt(sc.nextLine()));
        System.out.print("변경할 영화 제목 : ");
        upMovie.setTitle(sc.nextLine());
        System.out.print("변경할 가격 : ");
        upMovie.setPrice(Integer.parseInt(sc.nextLine()));
        String date = "";
        while (true) {
            System.out.print("변경할 개봉일(YYYY-MM-DD) : ");
            date = sc.nextLine();
            String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
            if (Pattern.matches(regex, date)) {
                break;
            } else {
                System.out.println("유효하지 않은 날짜 형식입니다.");
            }
        }
        upMovie.setReleaseDate(date);
        cnt = mDao.updateMovie(upMovie);
        if(cnt > 0) {
            System.out.println("영화 정보 변경 성공");
        } else {
            System.out.println("영화 정보 변경 실패");
        }
    }

    public void deleteMovie(Scanner sc) {
        System.out.print("삭제할 영화 번호 : ");
        int movieId = Integer.parseInt(sc.nextLine());
        int cnt = -1;
        cnt = mDao.deleteMovie(movieId);
        if (cnt > 0) {
            System.out.println("영화 삭제 성공");
        } else {
            System.out.println("영화 삭제 실패");
        }
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
    public void addTheater(Scanner sc) {
        int cnt = -1;
        Theater theater = new Theater();
        System.out.print("관 이름 : ");
        theater.setName(sc.nextLine());
        System.out.print("자리 수 : ");
        theater.setTotalSeats(Integer.parseInt(sc.nextLine()));
        cnt = tDao.addTheater(theater);
        if(cnt > 0) {
            System.out.println("관 추가 성공");
        } else {
            System.out.println("관 추가 실패");
        }
    }

    public void updateTheater(Scanner sc) {
        int cnt = -1;
        Theater upTheater = new Theater();
        System.out.print("영화관 번호 : ");
        upTheater.setTheaterId(Integer.parseInt(sc.nextLine()));
        System.out.print("변경할 영화관 이름 : ");
        upTheater.setName(sc.nextLine());
        cnt = tDao.updateTheater(upTheater);
        if(cnt > 0) {
            System.out.println("영화 정보 변경 성공");
        } else {
            System.out.println("영화 정보 변경 실패");
        }
    }

    public void deleteTheater(Scanner sc) {
        System.out.print("삭제할 영화관 번호 : ");
        int theaterId = Integer.parseInt(sc.nextLine());
        int cnt = -1;
        cnt = tDao.deleteTheater(theaterId);
        if (cnt > 0) {
            System.out.println("영화관 삭제 성공");
        } else {
            System.out.println("영화관 삭제 실패");
        }
    }

    public void getAllTheaters() {
        List<Theater> theaterList = tDao.getAllTheaters();
        if (theaterList != null) {
            System.out.printf("%-5s %-10s %-8s%n", "번호", "영화관이름", "좌석수");
            for (Theater bean : theaterList) {
                System.out.printf("%-5d %-10s %-8d%n",
                        bean.getTheaterId(),
                        bean.getName(),
                        bean.getTotalSeats());
            }
        } else {
            System.out.println("영화관 정보가 없습니다.");
        }
    }

    // ScheduleDAO
    public void addSchedule(Scanner sc) {
        int cnt = -1;
        Schedule schedule = new Schedule();
        System.out.print("관 번호 : ");
        schedule.setTheaterId(Integer.parseInt(sc.nextLine()));
        System.out.print("영화 번호 : ");
        schedule.setMovieId(Integer.parseInt(sc.nextLine()));
        String showTime = "";
        while (true) {
            System.out.print("상영시간(YYYY-MM-DD HH:MM) : ");
            showTime = sc.nextLine();
            String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) ([01]\\d|2[0-3]):([0-5]\\d)$";
            if (Pattern.matches(regex, showTime)) {
                break;
            } else {
                System.out.println("유효하지 않은 시간 형식입니다.");
            }
        }
        schedule.setShowTime(showTime);
        cnt = sDao.addSchedule(schedule);
        if(cnt > 0) {
            System.out.println("상영 계획 추가 성공");
        } else {
            System.out.println("상영 계획 추가 실패");
        }
    }

    public void getScheduleById(int scheduleId) {

    }

    public int getSchedulesByMovieId(Scanner sc) {
        System.out.print("영화번호로 선택 : ");
        int movieId = Integer.parseInt(sc.nextLine());
        List<Schedule> scheduleList = sDao.getSchedulesByMovieId(movieId);
        if (scheduleList != null) {
            System.out.printf("%-5s %-20s %-8s %-12s%n", "번호", "제목", "관", "상영시간");
            for (Schedule bean : scheduleList) {
                System.out.printf("%-5d %-20s %-8s %-16s%n",
                        bean.getScheduleId(),
                        bean.getTitle(),
                        bean.getName(),
                        bean.getShowTime());
            }
        } else {
            System.out.println("상영 예정이 없습니다.");
        }
        return movieId;
    }

    public void getSchedulesByTheater(int theaterId) {

    }

    public void getAllSchedules() {
        List<Schedule> scheduleList = sDao.getAllSchedules();
        if (scheduleList != null) {
            System.out.printf("%-5s %-20s %-8s %-12s%n", "번호", "제목", "관", "상영시간");
            for (Schedule bean : scheduleList) {
                System.out.printf("%-5d %-20s %-8s %-16s%n",
                        bean.getScheduleId(),
                        bean.getTitle(),
                        bean.getName(),
                        bean.getShowTime());
            }
        } else {
            System.out.println("영화관 정보가 없습니다.");
        }
    }

    public void updateSchedule(Scanner sc) {
        int cnt = -1;
        Schedule schedule = new Schedule();
        System.out.print("일정 번호 : ");
        schedule.setScheduleId(Integer.parseInt(sc.nextLine()));
        String showTime = "";
        while (true) {
            System.out.print("상영시간(YYYY-MM-DD HH:MM) : ");
            showTime = sc.nextLine();
            String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) ([01]\\d|2[0-3]):([0-5]\\d)$";
            if (Pattern.matches(regex, showTime)) {
                break;
            } else {
                System.out.println("유효하지 않은 시간 형식입니다.");
            }
        }
        schedule.setShowTime(showTime);
        cnt = sDao.updateSchedule(schedule);
        if(cnt > 0) {
            System.out.println("상영 계획 변경 성공");
        } else {
            System.out.println("상영 계획 변경 실패");
        }
    }

    public void deleteSchedule(Scanner sc) {
        System.out.print("삭제할 일정 번호 : ");
        int scheduleId = Integer.parseInt(sc.nextLine());
        int cnt = -1;
        cnt = sDao.deleteSchedule(scheduleId);
        if (cnt > 0) {
            System.out.println("일정 삭제 성공");
        } else {
            System.out.println("일정 삭제 실패");
        }
    }

    // ReservationDAO
    public void addReservation(Scanner sc, int movieId, Users loginUser) {
        System.out.print("상영 번호 : ");
        int scheduleId = Integer.parseInt(sc.nextLine());
        List<Reservation> reservationList = rDao.getReservationsBySchedule(scheduleId);
        printSeats(reservationList, 10, 10);
        System.out.print("예약할 자리(,로 여러개 입력도 가능) : ");
        String seat = sc.nextLine();
        String regex = "^([A-J](?:10|[1-9]))(,\\s*[A-J](?:10|[1-9]))*$\n";
        if (!Pattern.matches(regex, seat)) {
            return;
        }
        List<String> seats = Arrays.stream(seat.split(",")).map(String::strip).toList();
        int cnt = 1;
        Reservation reservation = new Reservation();
        reservation.setScheduleId(scheduleId);
        reservation.setUserId(loginUser.getUserId());
        reservation.setPrice(mDao.getMovieById(movieId).getPrice());
        for (String str : seats) {
            int rowNum =  (str.charAt(0) - 'A' + 1);
            int colNum = Integer.parseInt(str.substring(1));
            reservation.setRowNum(rowNum);
            reservation.setColNum(colNum);
            cnt *= rDao.addReservation(reservation);
        }
        if (cnt > 0) {
            System.out.println("좌석 예약 성공");
        } else {
            System.out.println("좌석 예약 실패. 관리자 호출 바람");
        }
    }

    public void getReservationById(int reservationId) {

    }

    public void getReservationsByUser(String userId) {

    }

    public void getReservationsBySchedule(int scheduleId) {

    }

    public void deleteReservation(int reservationId) {

    }

    // 예약된 좌석을 체크해서 콘솔에 좌석 배치 출력
    public void printSeats(List<Reservation> reservationList, int rowCount, int colCount) {
        // 예약 좌석을 빠르게 찾기 위해 Set으로 변환
        Set<String> reserved = new HashSet<>();
        for (Reservation r : reservationList) {
            reserved.add(r.getRowNum() + "-" + r.getColNum());
            // 예: "3-5" 형태로 저장
        }

        // 행은 문자 (A,B,C...)로 표현
        for (int row = 1; row <= rowCount; row++) {
            char rowLabel = (char) ('A' + row - 1); // 1 → A, 2 → B ...
            System.out.print(rowLabel + " ");

            for (int col = 1; col <= colCount; col++) {
                String key = row + "-" + col;
                if (reserved.contains(key)) {
                    System.out.print("■ "); // 예약됨
                } else {
                    System.out.print("□ "); // 예약 가능
                }
            }
            System.out.println(); // 줄바꿈
        }

        // 열 번호 표시 (옵션)
        System.out.print("   ");
        for (int col = 1; col <= colCount; col++) {
            System.out.printf("%2d", col);
        }
        System.out.println();
    }

}
