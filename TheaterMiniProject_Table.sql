DROP TABLE MOVIE;
DROP TABLE THEATER;
DROP TABLE SCREENING;
DROP TABLE SEAT;
DROP TABLE RESERVATION;
DROP SEQUENCE MOVIE_SEQ;
DROP SEQUENCE THEATER_SEQ;
DROP SEQUENCE SCREENING_SEQ;
DROP SEQUENCE SEAT_SEQ;
DROP SEQUENCE RESERVATION_SEQ;
CREATE SEQUENCE MOVIE_SEQ;
CREATE SEQUENCE THEATER_SEQ;
CREATE SEQUENCE SCREENING_SEQ;
CREATE SEQUENCE SEAT_SEQ;
CREATE SEQUENCE RESERVATION_SEQ;
-- 영화 정보
CREATE TABLE Movie (
    movie_id INT PRIMARY KEY DEFAULT MOVIE_SEQ,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    running_time INT, -- 분 단위
    release_date DATE
);

-- 상영관
CREATE TABLE Theater (
    theater_id INT PRIMARY KEY DEFAULT THEATER_SEQ,
    name VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL
);

-- 상영 일정
CREATE TABLE Screening (
    screening_id INT PRIMARY KEY DEFAULT SCREENING_SEQ,
    movie_id INT NOT NULL,
    theater_id INT NOT NULL,
    screening_time DATETIME NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES Movie(movie_id),
    FOREIGN KEY (theater_id) REFERENCES Theater(theater_id)
);

-- 좌석 (좌석은 상영관별로 고정되어 있고, 예약 상태는 Reservation에서 관리)
CREATE TABLE Seat (
    seat_id INT PRIMARY KEY DEFAULT SEAT_SEQ,
    theater_id INT NOT NULL,
    row_num INT NOT NULL,
    col_num INT NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES Theater(theater_id)
);

-- 예약 정보
CREATE TABLE Reservation (
    reservation_id INT PRIMARY KEY DEFAULT RESERVATION_SEQ,
    screening_id INT NOT NULL,
    seat_id INT NOT NULL,
    customer_name VARCHAR(50),
    reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (screening_id) REFERENCES Screening(screening_id),
    FOREIGN KEY (seat_id) REFERENCES Seat(seat_id),
    UNIQUE (screening_id, seat_id) -- 중복 예약 방지
);
