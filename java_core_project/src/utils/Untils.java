package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Untils {
    public static int inputInteger(Scanner sc){
        do {
            try {
                int n = Integer.parseInt(sc.nextLine());
                return n;
            }catch (Exception e){
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập số nguyên");
            }
        }while (true);
    }
    public static double inputDouble(Scanner sc){
        do {
            try {
                double n = Double.parseDouble(sc.nextLine());
                return n;
            }catch (Exception e){
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập số thực");
            }
        }while (true);
    }
    // xữ lý ngoại lệ password
    public static Pattern chechtrycatchPassWork(Scanner sc){
        do {
            try {
                Pattern n = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{7,15}$");
                return n;
            }catch (Exception e){
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập đúng quy tắc đặt password (gợi ý: Password1, Test123)");
            }
        }while (true);
    }

    public static LocalDate convertStringToDate(Scanner sc, String dateTest) {
        do {
            String time = sc.nextLine();
            try {
                LocalDate birthDate = LocalDate.parse(time, DateTimeFormatter.ofPattern(dateTest));
                return birthDate;
            } catch (Exception e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng " + dateTest);
            }
        } while (true);
    }

}
