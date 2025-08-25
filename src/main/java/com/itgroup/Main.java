package com.itgroup;

import com.itgroup.bean.Users;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataManager manager = new DataManager();
        Users loginUser = null;
        while (true) {
            if (loginUser == null) { // 로그인 전
                System.out.println("메뉴 선택");
                System.out.println("0.종료, 1.회원가입, 2.로그인");
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu) {
                    case 0:
                        sc.close();
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                    case 1:
                        loginUser = manager.signUp(sc);
                        break;
                    case 2:
                        loginUser = manager.login(sc);
                        break;
                    default:
                }
            } else { // 로그인 후
                System.out.println("메뉴 선택");
                System.out.println("0.로그아웃, 1.내정보, 2.영화관련");
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu) {
                    case 0:
                        loginUser = null;
                        break;
                    case 1:
                        loginUser = manager.userInfo(sc, loginUser);
                        break;
                    case 2:
                        manager.getAllMovies();
                        System.out.println("0.돌아가기, 1.예약, 2.추가, 3.수정, 4.삭제");
                        menu = Integer.parseInt(sc.nextLine());
                        switch (menu) {
                            case 0:
                                break;
                            case 1:
                                manager.addReservation(sc);
                                break;
                            case 2:
                                manager.addMovie(sc);
                                break;
                            case 3:
                                manager.updateMovie(sc);
                                break;
                            case 4:
                                manager.deleteMovie(sc);
                                break;
                            default:
                        }
                        break;
                    default:
                }
            }

        }
    }
}