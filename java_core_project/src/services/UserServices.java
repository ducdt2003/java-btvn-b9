
package services;


import entities.User;
import entities.Work;
import utils.Constant;
import utils.Untils;
import views.LoginMenu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserServices {
    private String userName;
    private String passWord;
    private String email;
    private String role;
    public void initializeBossAccount(ArrayList<User> users) {
        if (users.isEmpty()) {
            User boss = new User("KAISHA", "boss@example.com", "KAISHA", Constant.ROLE_BOSS);
            users.add(boss);
            System.out.println("Xếp quản lý dự án công việc này là: " + boss.getUsername());
        }
    }
    public User inputRegister(Scanner sc, ArrayList<User> users) {
        System.out.println("---Tạo Tài Khoản---");

        // Nhập tên tài khoản
        System.out.print("Nhập userName: ");
        inputUserName(sc, users); // Gọi phương thức nhập tên tài khoản

        // Nhập email
        System.out.print("Nhập email: ");
        inputEmail(sc, users); // Gọi phương thức nhập email

        // Nhập mật khẩu
        System.out.print("Nhập passWord: ");
        inputPassWord(sc); // Gọi phương thức nhập mật khẩu


        String role = Constant.ROLE_STAFF;
        User user = new User(userName, email, passWord, role); // Tạo đối tượng User mới
        users.add(user); // Thêm người dùng vào danh sách
        System.out.println("Tài khoản đã được tạo thành công với vai trò: " + role);

        return user; // Trả về người dùng vừa tạo
    }


    public void inputUserName(Scanner sc, ArrayList<User> users) {
        while (true) {
            userName = sc.nextLine(); // Nhập tên tài khoản
            if (findUserByUsername(userName, users) != null) {
                System.out.println("Tài khoản đã tồn tại! Hãy chọn tên tài khoản khác.");
            } else {
                System.out.println("Tài khoản hợp lệ! Tiếp tục nhập email.");
                break; // Thoát khỏi vòng lặp nếu tên tài khoản hợp lệ
            }
        }
    }

    public void inputEmail(Scanner sc, ArrayList<User> users) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+@[a-z]+\\.[a-z]{2,}$");
        while (true) {
            email = sc.nextLine(); // Nhập email
            if (pattern.matcher(email).matches()) {
                if (findUserByEmail(email, users) != null) {
                    System.out.println("Email này đã được đăng ký từ trước rồi! Nhập email khác.");
                } else {
                    System.out.println("Email hợp lệ! Tiếp tục nhập mật khẩu.");
                    break; // Thoát khỏi vòng lặp nếu email hợp lệ
                }
            } else {
                System.out.println("Lỗi khi đặt tên email.");
                System.out.print("Nhập lại email đúng quy tắc ---@gmail.com: ");
            }
        }
    }

    public void inputPassWord(Scanner sc) {
        Pattern pattern = Untils.chechtrycatchPassWork(sc);
        while (true) {
            passWord = sc.nextLine(); // Nhập mật khẩu
            if (pattern.matcher(passWord).matches()) {
                break; // Thoát khỏi vòng lặp nếu mật khẩu hợp lệ
            } else {
                System.out.print("vui lòng nhập đúng quy tắc đặt password (gợi ý: Password1, Test123) ");
            }
        }
    }

    public void displayRegisters(ArrayList<User> users) {
        if (users.isEmpty()) {
            System.out.println("Danh sách tài khoản trống!");
        } else {
            System.out.println("Danh sách thành viên:");
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail()
                        + ", Role: " + user.getRole());
            }
        }
    }


    public void checkLogin(Scanner sc, ArrayList<User> users) {
        // Khởi tạo tài khoản sếp nếu chưa có
        if (users.isEmpty()) {
            User boss = new User("KAISHA", "boss@gmail.com", "KAISHA", Constant.ROLE_BOSS);
            users.add(boss);
            System.out.println("Tài khoản sếp được khởi tạo: " + boss.getUsername());
        }

        while (true) {
            System.out.println("Nhập tài khoản của bạn: ");
            String userName = sc.nextLine();
            System.out.println("Nhập mật khẩu của bạn: ");
            String passWord = sc.nextLine();
            boolean userFound = false;

            for (User user1 : users) {
                // Kiểm tra tài khoản và mật khẩu
                if (user1.getUsername().equals(userName) && user1.getPassword().equals(passWord)) {
                    // Đăng nhập thành công
                    System.out.println("Chào mừng --> " + userName + " với ID: " + user1.getId() + " và Role: " + user1.getRole() + " <-- đã đăng nhập thành công");
                    // Kiểm tra vai trò và chuyển đến menu phù hợp
             /*       if (user1.getRole().equals(Constant.ROLE_BOSS)) { // đảo ngược lại
                        LoginMenu loginMenu = new LoginMenu();
                        loginMenu.forBoss(sc, user1, users);  // Truyền user1 để sử dụng đúng người dùng
                    } else if (user1.getRole().equals(Constant.ROLE_MANAGE)) {
                        System.out.println("Bạn đã đăng nhập với vai trò trưởng phòng.");
                        LoginMenu loginMenu = new LoginMenu();
                        loginMenu.forManage(sc, user1, users);  // Truyền user1
                    } else if (user1.getRole().equals(Constant.ROLE_STAFF)) {
                        System.out.println("Bạn đã đăng nhập với vai trò nhân viên.");
                        LoginMenu loginMenu = new LoginMenu();
                        loginMenu.forStaff(sc, user1, users);  // Truyền user1
                    }*/
                    if (Constant.ROLE_BOSS.equals(user1.getRole())) { // đảo ngược lại
                        LoginMenu loginMenu = new LoginMenu();
                        loginMenu.forBoss(sc, user1, users);  // Truyền user1 để sử dụng đúng người dùng
                    } else if (Constant.ROLE_MANAGE.equals(user1.getRole())) {
                        System.out.println("Bạn đã đăng nhập với vai trò trưởng phòng.");
                        LoginMenu loginMenu = new LoginMenu();
                        loginMenu.forManage(sc, user1, users);  // Truyền user1
                    } else if (Constant.ROLE_STAFF.equals(user1.getRole())) {
                        System.out.println("Bạn đã đăng nhập với vai trò nhân viên.");
                        LoginMenu loginMenu = new LoginMenu();
                        loginMenu.forStaff(sc, user1, users);  // Truyền user1
                    }

                    userFound = true;
                    break; // Thoát khỏi vòng lặp for sau khi đăng nhập thành công
                }
            }

            if (!userFound) {
                // Nếu tài khoản hoặc mật khẩu không chính xác
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng!");

                System.out.println("Bạn có muốn quên mật khẩu không? (yes/no)");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    forgotPassword(sc, users);
                    return; // Thoát khỏi hàm sau khi thực hiện quên mật khẩu
                }
            }
        }
    }


   /* public void checkLogin(Scanner sc, User user, ArrayList<User> users) {
        // Khởi tạo tài khoản sếp nếu chưa có
        if (users.isEmpty()) {
            User boss = new User("KAISHA", "boss@gmail.com", "KAISHA", Constant.ROLE_BOSS);
            users.add(boss);
            System.out.println("Tài khoản sếp được khởi tạo: " + boss.getUsername());
        }

        while (true) {
            System.out.println("Nhập tài khoản của bạn: ");
            String userName = sc.nextLine();
            System.out.println("Nhập mật khẩu của bạn: ");
            String passWord = sc.nextLine();
            boolean userFound = false;

            for (User user1 : users) {
                if (user1.getUsername().equals(userName)) {
                    // Tài khoản tồn tại, kiểm tra mật khẩu
                    if (user1.getPassword().equals(passWord)) {
                        // Đăng nhập thành công
                        System.out.println("Chào mừng --> " + userName + " với Role: " + user1.getRole() + " <-- đã đăng nhập thành công");

                        // Kiểm tra vai trò
                        if (user1.getRole().equals(Constant.ROLE_BOSS)) {
                            LoginMenu loginMenu = new LoginMenu();
                            loginMenu.forBoss(sc,user ,users);
                        } else if (user1.getRole().equals(Constant.ROLE_MANAGE)) {
                            System.out.println("Bạn đã đăng nhập với vai trò trưởng phòng.");
                            LoginMenu loginMenu = new LoginMenu();
                            loginMenu.forManage(sc,user, users);
                        } else if (user1.getRole().equals(Constant.ROLE_STAFF)) {
                            System.out.println("Bạn đã đăng nhập với vai trò nhân viên.");
                            LoginMenu loginMenu = new LoginMenu();
                            loginMenu.forStaff(sc, user, users);
                        }

                        userFound = true;
                        break; // Thoát khỏi vòng lặp for
                    } else {
                        // Mật khẩu sai
                        System.out.println("Mật khẩu không chính xác!");
                        System.out.println("Bạn có muốn quên mật khẩu không? (yes/no)");
                        String choice = sc.nextLine();
                        if (choice.equalsIgnoreCase("yes")) {
                            forgotPassword(sc, users);
                            return; // Thoát khỏi hàm sau khi thực hiện quên mật khẩu
                        }
                        userFound = true; // Tài khoản tồn tại
                        break; // Thoát khỏi vòng lặp for
                    }
                }
            }
            if (!userFound) {
                // Nếu tài khoản không tồn tại
                System.out.println("Tên đăng nhập không đúng!");
            }
            // Yêu cầu nhập lại thông tin nếu chưa đăng nhập thành công
            System.out.println("Nhập lại tài khoản của bạn: ");
        }
    }*/

    public void forgotPassword(Scanner sc, ArrayList<User> users) {
        while (true) {
            System.out.print("Nhập email đã liên kết với tài khoản: ");
            String email1 = sc.nextLine();
            User user = findUserByEmail(email1, users);
            if (user != null) {
                System.out.print("Email chính xác! Mời bạn nhập mật khẩu mới: ");
                String newPassword = sc.nextLine();
                Pattern pattern = Untils.chechtrycatchPassWork(sc);
                if (pattern.matcher(newPassword).matches()) {
                    user.setPassword(newPassword); // Cập nhật mật khẩu mới
                    System.out.println("Mật khẩu đã được cập nhật. Vui lòng đăng nhập lại.");
                    checkLogin(sc, users);
                    break; // Thoát khỏi hàm sau khi cập nhật mật khẩu
                } else {
                    System.out.print("\nVui lòng nhập đúng quy tắc đặt password (gợi ý: Password1, Test123) ");
                }
            } else {
                System.out.println("Email chưa được liên kết với tài khoản nào! Vui lòng thử lại.");
            }
        }
    }



    // Đổi username
    public void changeUserName(Scanner sc, User user, ArrayList<User> users) {
        System.out.println("Mời bạn nhập username mới: ");
        String newUserName = sc.nextLine();
        if (findUserByUsername(newUserName, users) != null) {
            System.out.println("Tài khoản đã tồn tại! Vui lòng chọn tên tài khoản khác.");
        } else {
            user.setUsername(newUserName);
            System.out.println("Tài khoản đã được đổi thành công!");
        }
    }

    // Đổi email
    public void changeEmail(Scanner sc, User user, ArrayList<User> users) {
        System.out.println("Mời bạn nhập email mới: ");
        String newEmail = sc.nextLine();

        // Kiểm tra email đã tồn tại hay chưa
        if (findUserByEmail(newEmail, users) != null) {
            System.out.println("Email này đã được đăng ký! Vui lòng chọn email khác.");
        } else {
            user.setEmail(newEmail);
            System.out.println("Email đã được đổi thành công!");
        }
    }

    // Đổi mật khẩu
    public void changePassWord(Scanner sc, User user) {
        System.out.println("Mời bạn nhập mật khẩu mới: ");
        String newPassWord = sc.nextLine();
        user.setPassword(newPassWord);
        System.out.println("Mật khẩu đã được đổi thành công!");
    }

    // Tìm kiếm username trong danh sách người dùng
    private User findUserByUsername(String userName, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user; // Trả về người dùng tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    // Tìm kiếm email trong danh sách người dùng
    private User findUserByEmail(String email, ArrayList<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user; // Trả về người dùng tìm thấy
            }
        }
        return null; // Không tìm thấy
    }
}
