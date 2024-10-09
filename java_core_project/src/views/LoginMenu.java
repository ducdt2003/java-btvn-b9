package views;

import data.Database;
import entities.Project;
import entities.User;
import entities.Work;
import services.ProjectService;
import services.TasksService;
import services.UserServices;
import services.WorkService;
import utils.Untils;

import javax.imageio.event.IIOWriteWarningListener;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenu {


    // thêm digi patter
    ProjectService projectService = ProjectService.getInstance();
    WorkService workService = WorkService.getInstance();
    TasksService tasksService = TasksService.getInstance();

    public void displayMenu(Scanner sc) {
        System.out.println("=========================================");
        System.out.println("          CHỌN CHỨC NĂNG               ");
        System.out.println("=========================================");
        System.out.println("| 1 | Đăng nhập                       |");
        System.out.println("| 2 | Đăng ký                         |");
        System.out.println("=========================================");
        System.out.print("Hãy lựa chọn chức năng: ");
    }


    public void selectDisplayMenu(Scanner sc, User user, ArrayList<User> users){
        do {
            displayMenu(sc);
            int choice = Untils.inputInteger(sc);
            UserServices userRegisterServices = new UserServices();
            switch (choice){
                case 1:
                    userRegisterServices.checkLogin(sc,users);
                    break;
                case 2:
                    userRegisterServices.initializeBossAccount(users);
                    user = userRegisterServices.inputRegister(sc, users);
                    userRegisterServices.displayRegisters(users);
                    userRegisterServices.checkLogin(sc, users);
                default:
                    System.out.println("lỗi đang nhập");
            }
        }while (true);
    }


    // khi đăng nhập sai mật khẩu
    public void inputPasswordMenu(Scanner sc) {
        System.out.println("=========================================");
        System.out.println("            SAI MẬT KHẨU               ");
        System.out.println("=========================================");
        System.out.println("| 1 | Đăng nhập lại                   |");
        System.out.println("| 2 | Quên mật khẩu                   |");
        System.out.println("| 0 | Thoát! Nhớ nhập lại nhé        |");
        System.out.println("=========================================");
        System.out.print("Hãy chọn tùy chọn bạn muốn: ");
    }

    public void selectPassWordMenu(Scanner sc, User user, ArrayList<User> users){
        inputPasswordMenu(sc);
        int choice = Untils.inputInteger(sc);
        UserServices userRegisterServices = new UserServices();
        switch (choice){
            case 1:
                userRegisterServices.checkLogin(sc,users);
                break;
            case 2:
                userRegisterServices.forgotPassword(sc, users);
                break;
            case 0:
                System.out.println("Thoát chương trình!");
                System.exit(0);
            default:
                System.out.println("--------------");
        }
    }



    // khi đăng nhập thành công chức năng quản lý tài khoản
    public void loginMenu() {
        System.out.println("=========================================");
        System.out.println("         QUẢN LÝ TÀI KHOẢN            ");
        System.out.println("=========================================");
        System.out.println("| 111 | Thay đổi username             |");
        System.out.println("| 222 | Thay đổi email                |");
        System.out.println("| 333 | Thay đổi mật khẩu             |");
        System.out.println("| 444 | Đăng xuất                      |");
        System.out.println("|  0  | Quay lại                       |");
        System.out.println("=========================================");
        System.out.print("Lựa chọn của bạn: ");
    }


    public void selectLoginMenu(Scanner sc,  User user, ArrayList<User> users){
        do {
            loginMenu();
            int choice = Untils.inputInteger(sc);
            UserServices userRegisterServices = new UserServices();
            switch (choice) {
                case 111:
                    userRegisterServices.changeUserName(sc, user, users);
                    selectDisplayMenu(sc, user, users);
                    break;
                case 222:
                    userRegisterServices.changeEmail(sc, user, users);
                    selectDisplayMenu(sc, user, users);
                    break;
                case 333:
                    userRegisterServices.changePassWord(sc, user);
                    selectDisplayMenu(sc, user, users);
                    break;
                case 444:
                    System.out.println("---Đăng xuất thành công---");
                    selectDisplayMenu(sc, user, users);
                    break;
                default:
                    System.out.println("Lỗi hệ thống");
            }
        }while (true);
        }

    // menu của boss
    public void displayMenuBoss() {
        System.out.println("=========================================");
        System.out.println("         CHỨC NĂNG CỦA BOSS           ");
        System.out.println("=========================================");
        System.out.println("| 1. | Quản lý dự án                  |");
        System.out.println("| 2. | Quản lý công việc              |");
        System.out.println("| 3. | Phân công công việc theo ID nhân sự |");
        System.out.println("| 4. | Quản lý thành viên              |");
        System.out.println("| 5. | Xem báo cáo                     |");
        System.out.println("| 6. | Xem lịch sử                     |");
        System.out.println("| 7. | Nhận xét                       |");
        System.out.println("| 999. | Quản lý tài khoản             |");
        System.out.println("=========================================");
        System.out.print("Hãy chọn chức năng bạn muốn: ");
    }

    // tính năng trong chức năng số 1 của boss (quản lý dự án)

    public void projectManagement(Scanner sc) {
        System.out.println("=========================================");
        System.out.println("            CÁC TÍNH NĂNG              ");
        System.out.println("=========================================");
        System.out.println("| 1. | Thêm mới Dự án                 |");
        System.out.println("| 2. | Tìm kiếm dự án                 |");
        System.out.println("| 3. | Xóa dự án theo ID và tên       |");
        System.out.println("| 4. | Thay đổi/chỉnh sửa dự án       |");
        System.out.println("| 5. | Xem dự án                      |");
        System.out.println("| 0. | Thoát chương trình             |");
        System.out.println("=========================================");
        System.out.print("Mời bạn lựa chọn: ");
    }


    public void selectProjectManagement(Scanner sc, User user, ArrayList<User>users){
        do {
            projectManagement(sc);
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    System.out.println("-----Thêm mới dự án-----");
                    projectService.inputProject(sc);
                    break;
                case 2:
                    System.out.println("*********************************");
                    System.out.println("       TÌM KIẾM DỰ ÁN          ");
                    System.out.println("*********************************");
                    System.out.println("1. Tìm kiếm theo ID            ");
                    System.out.println("2. Tìm kiếm theo tên           ");
                    System.out.println("*********************************");
                    System.out.print("Mời bạn chọn tính năng: ");
                    int choiceFind = Untils.inputInteger(sc);
                    if (choiceFind == 1){
                        projectService.displayFindByProjecId(sc);
                    }else if (choiceFind == 2){
                        projectService.findByProjectName(sc);
                    }else if (choiceFind < 1 || choiceFind > 2){
                        System.out.println("Chưa cập nhật chức năng " + choiceFind);
                    }else {
                        System.out.println("Lỗi nhập vào");
                    }
                    break;
                case 3:
                    System.out.println("-----Xóa dự án theo id và tên-----");
                    projectService.remove(sc);
                    break;
                case 4:
                    System.out.println("-----Sữa dự án theo id và tên-----");
                    projectService.edit(sc);
                    break;
                case 5:
                    System.out.println("-----Danh sách dự án-----");
                    projectService.display(sc);
                    break;
                case 0:
                    System.out.println("Đã thoát khỏi menu của tính năng của chức năng số 1 quản lý dự án");
                    forBoss(sc, user, users);
                default:
                    System.out.println("Lỗi Nhập vào");
                    forBoss(sc, user, users);
            }
        }while (true);

    }


    // chức năng số 2
    public void workAssignment() {
        System.out.println("=====================================");
        System.out.println("        THÔNG TIN CÁC TÍNH NĂNG     ");
        System.out.println("=====================================");
        System.out.println("| 1. | Hiển thị tất cả dự án       |");
        System.out.println("| 2. | Thêm công việc cho dự án     |");
        System.out.println("| 3. | Xóa công việc cho dự án hiện tại |");
        System.out.println("| 4. | Sửa công việc cho dự án hiện tại |");
        System.out.println("| 5. | Xem công việc                |");
        System.out.println("| 6. | Tìm kiếm công việc           |");
        System.out.println("| 0. | Thoát khỏi chức năng quản lý phân công công việc |");
        System.out.println("=====================================");
        System.out.print("Mời bạn chọn tính năng: ");
    }


    public void selectWorkAssignment(Scanner sc,User user, ArrayList<User>users){
        do {
            workAssignment();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    projectService.display(sc);
//                    selectWorkAssignment(sc, user, users);
                    break;
                case 2:
                    projectService.display(sc);
                    workService.addWorkToProject(sc);
//                    selectWorkAssignment(sc, user, users);
                    break;
                case 3:
                    workService.remove(sc);
//                    selectWorkAssignment(sc, user, users);
                    break;
                case 4:
                    workService.edit(sc);
//                    selectWorkAssignment(sc, user, users);
                    break;
                case 5:
                    workService.display(sc);
//                    selectWorkAssignment(sc, user, users);
                    break;
                case 6:
                    System.out.println("*********************************");
                    System.out.println("       TÌM KIẾM CÔNG VIỆC          ");
                    System.out.println("*********************************");
                    System.out.println("1. Tìm kiếm theo ID            ");
                    System.out.println("2. Tìm kiếm theo tên           ");
                    System.out.println("*********************************");
                    System.out.print("Mời bạn chọn tính năng: ");

                    int choiceFind = Untils.inputInteger(sc);
                    if (choiceFind == 1){
                        workService.findAndDisplayWorkByIdInProject(sc);
                    }else if (choiceFind == 2){
                        workService.findWorksByNameInProject(sc);
                    }else if (choiceFind < 1 || choiceFind > 2){
                        System.out.println("Chưa cập nhật chức năng " + choiceFind);
                    }else {
                        System.out.println("Lỗi nhập vào");
                    }
                    break;
                case 0:
                    System.out.println("thoát khỏi menu tính năng của chức năng 2 quản lý phân công nhiệm vụ");
                    forBoss(sc,user, users);
                default:
                    System.out.println("Lỗi nhập vào");
                    forBoss(sc,user, users);
            }
        }while (true);

    }


    // chức năng số 9 xem lịch sư
    public void viewHistory() {
        System.out.println("=========================================");
        System.out.println("          CHỌN CHỨC NĂNG                ");
        System.out.println("=========================================");
        System.out.println("| 1 | Xem lịch sử dự án               |");
        System.out.println("| 2 | Xem lịch sử công việc           |");
        System.out.println("| 0 | Thoát tính năng xem lịch sử      |");
        System.out.println("=========================================");
        System.out.print("Hãy lựa chọn chức năng: ");
    }

    public void selectViewHistory(Scanner sc, User user, ArrayList<User> users){
        do {
            viewHistory();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    System.out.println("Các dự án");
                    /*projectService.display(sc);
                    System.out.println("Thông thông tin chi tiết dự án bạn muốn xem");
                    projectService.findBy(sc);*/
                    projectService.viewHistoryProject();
                    break;
                case 2:
                    System.out.println("Các công việc");
                    workService.display(sc);
                    break;
                case 0:
                    System.out.println("Đã thoát khỏi tính năng xem lịch sữ");
                    forBoss(sc, user, users);
                default:
                    System.out.println("Lỗi nhập vào");
                    forBoss(sc, user, users);
            }
        }while (true);

    }


    // menu chức năng số 5 quản lý thành viên
    public void managerStaff() {
        System.out.println("=========================================");
        System.out.println("        TÍNH NĂNG QUẢN LÝ THÀNH VIÊN   ");
        System.out.println("=========================================");
        System.out.println("| 1 | Thay đổi chức vụ                 |");
        System.out.println("| 2 | Xem thành viên                   |");
        System.out.println("| 0 | Thoát chức năng quản lý thành viên|");
        System.out.println("=========================================");
        System.out.print("Hãy chọn tính năng: ");
    }

    public void selectManagerStaff(Scanner sc,User user, ArrayList<User> users){
        do {
            managerStaff();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    UserServices userServices = new UserServices();
                    userServices.displayRegisters(users);
                    System.out.println("==========================================");
                    System.out.println("      CHỌN TÍNH NĂNG BẠN MUỐN          ");
                    System.out.println("==========================================");
                    System.out.println("| 1 | Từ nhân viên lên trưởng phòng     |");
                    System.out.println("| 2 | Từ trưởng phòng xuống nhân viên    |");
                    System.out.println("==========================================");
                    System.out.print("Hãy chọn tính năng: ");

                    int choice1 = Untils.inputInteger(sc);
                    if (choice1 == 1){
                        workService.appointManagerById(sc, users);
                    } else if ( choice1 == 2) {
                        workService.demoteManagerById(sc, users);
                    }
                    break;
                case 2:
                    workService.displayStaffAndManagers(users);
                    break;
                case 0:
                    System.out.println("Thoát chương trình");
                    forBoss(sc,user, users);
                default:
                    System.out.println("lỗi đăng nhập");
                    break;
            }
        }while (true);
    }


    public void forBoss(Scanner sc,  User user, ArrayList<User> users){
        do {
            displayMenuBoss();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    selectProjectManagement(sc, user, users);
                    break;
                case 2:
                    selectWorkAssignment(sc, user, users);
                    break;
                case 3:
                    workService.assignByUserId(sc, users);
                    break;
 /*               case 4:
                    workService.notification(sc, users);
                    break;*/
                case 4:
                    selectManagerStaff(sc, user, users);
                    break;
                case 5:
                    workService.displayNotifications(user);
                    break;
                case 6:
                    selectViewHistory(sc, user, users);
                    break;
                case 7:
                    workService.commentToBoss(sc, users);
                    break;
                case 999:
                    selectLoginMenu(sc,user, users);
                default:
                    System.out.println("Chức năng bạn nhập chưa được cập nhật hãy chọn chức năng khác!");
            }
        }while (true);
    }


    // menu của manage
    public void diplayMenuManage() {
        System.out.println("===========================================");
        System.out.println("           CHỨC NĂNG CỦA MANAGE          ");
        System.out.println("===========================================");
        System.out.println("| 1 | Công việc của tôi                   |");
        System.out.println("| 2 | Quản lý nhiệm vụ                    |");
        System.out.println("| 3 | Phân công nhiệm vụ                  |");
        System.out.println("| 4 | Báo cáo kết quả cho sếp (BOSS)      |");
        System.out.println("| 5 | Xem lịch sử công việc               |");
        System.out.println("| 6 | Xem đánh giá từ cấp trên            |");
        System.out.println("| 7 | Xem báo cáo từ cấp dưới             |");
        System.out.println("| 8 | Nhận xét Nhiệm vụ                   |");
        System.out.println("|999 | Quản lý tài khoản                  |");
        System.out.println("===========================================");
        System.out.print("Hãy Chọn chức năng bạn muốn: ");
    }


    public void taskManagement() {
        System.out.println("===============================================");
        System.out.println("          QUẢN LÝ NHIỆM VỤ                   ");
        System.out.println("===============================================");
        System.out.println("| 1 | Thêm Nhiệm Vụ                        |");
        System.out.println("| 2 | Xóa Nhiệm Vụ                         |");
        System.out.println("| 3 | Sửa Nhiệm Vụ                         |");
        System.out.println("| 4 | Tìm Kiếm Nhiệm Vụ                    |");
        System.out.println("| 5 | Xem Danh Sách Nhiệm Vụ               |");
        System.out.println("| 0 | Thoát Khỏi Tính Năng Quản Lý Nhiệm Vụ |");
        System.out.println("===============================================");
        System.out.print("Mời bạn chọn tính năng: ");
    }

    public void selecttaskManagement(Scanner sc, User user, ArrayList<User> users){
        do {
            taskManagement();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    tasksService.addTaskByWorkId(sc);
                    break;
                case 2:
                    tasksService.remove(sc);
                    break;
                case 3:
                    tasksService.edit(sc);
                    break;
                case 4:
                    System.out.println("*********************************");
                    System.out.println("       TÌM KIẾM Nhiệm vụ         ");
                    System.out.println("*********************************");
                    System.out.println("1. Tìm kiếm theo ID            ");
                    System.out.println("2. Tìm kiếm theo tên           ");
                    System.out.println("*********************************");
                    System.out.print("Mời bạn chọn tính năng: ");

                    int choiceFind = Untils.inputInteger(sc);
                    if (choiceFind == 1){
                        tasksService.findById(sc);
                    }else if (choiceFind == 2){
                        tasksService.searchTaskByName(sc);
                    }else if (choiceFind < 1 || choiceFind > 2){
                        System.out.println("Chưa cập nhật chức năng " + choiceFind);
                    }else {
                        System.out.println("Lỗi nhập vào");
                    }
                    break;
                case 5:
                    tasksService.display(sc);
                    break;
                case 0:
                    forManage(sc,user, users);
                default:
                    System.out.println("Chức năng bạn nhập chưa được cập nhật hãy chọn chức năng khác!");

            }
        }while (true);
    }


    public void forManage(Scanner sc, User user, ArrayList<User> users){
        do {
            diplayMenuManage();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    // công việc của tôi
                 /*  workService.viewAssignedWorksByUserId(sc, user);*/
                   /* workService.viewAssignedWorksByUserId(sc,user);*/
                    workService.findByAssignee(user.getId());
                    break;
                case 2:
                    // quản lý nhiệm vụ
                    selecttaskManagement(sc, user, users);
                    break;
                case 3:
                    // phân công nhiệm vụ
                    tasksService.assignTaskToStaff(sc, user, users);
                    break;
               /* case 4:
                    System.out.println("Thông báo");
                    System.out.println("1. Từ cấp trên ");
                    System.out.println("2. từ cấp dưới");
                    int choiceTb = Untils.inputInteger(sc);
                    if (choiceTb == 1){
                        workService.viewNotifications(user.getId());
                    } else if (choiceTb == 2){
                        // chưa làm
                    }else {
                        System.out.println("Lỗi Nhập Vào");
                    }
                    break;*/
                case 4:
                    workService.reportWorkToBoss(sc, user, users);
                    break;
                case 5:
//                    tasksService.sendNotification(sc, users);
                    // xem lịch sữ
                    System.out.println("Xem lịch sữ");
                    System.out.println("1. Lịch sữ tạo nhiệm vụ");
                    System.out.println("2. Lịch sữ bàn giao nhiệm vụ");
                    System.out.println("mời bạn chọn");
                    int choiceLs = Untils.inputInteger(sc);
                    if (choiceLs == 1){
                        // lịch sữ tạo nhiệm vụ
                        tasksService.viewTaskCreationHistory();
                    } else if (choiceLs == 2) {
                        // lịch sữa giao nhiệm vụ
                        tasksService.viewTaskAssignmentHistory();
                    }else {
                        System.out.println("Lỗi Nhập Vào");
                    }
                    break;
                case 6:
                    // xem thông báo từ cấp trên
                    workService.viewNotifications(user.getId());
                    break;
                case 7:
                    // xem báo cáo từ câ dưới
//                    tasksService.displayReport(user);
                    tasksService.displayTaskEvaluationsIsmanage(user.getId(),users);
                    break;
                case 8:
                    tasksService.sendTaskEvaluation(sc, users);
                    break;
                case 999:
                    selectLoginMenu(sc,user, users);
                default:
                    System.out.println("Chức năng bạn nhập chưa được cập nhật hãy chọn chức năng khác!");
            }
        }while (true);
    }



// Nhập menu của staff
public void displayMenuStaff() {
    System.out.println("===========================================");
    System.out.println("             CHỨC NĂNG CỦA STAFF         ");
    System.out.println("===========================================");
    System.out.println("| 1 | Công việc của tôi                   |");
    System.out.println("| 2 | Thực hiện nhiệm vụ                  |");
    System.out.println("| 3 | Báo cáo kết quả                     |");
    System.out.println("| 4 | Xem đánh giá từ câp trên            |");
    System.out.println("|999 | Quản lý tài khoản                   |");
    System.out.println("===========================================");
    System.out.print("Hãy chọn chức năng bạn muốn: ");
}

// chức năng số 2 của nhân viên

    public void forStaff(Scanner sc, User user, ArrayList<User> users){
        do {
            displayMenuStaff();
            int choice = Untils.inputInteger(sc);
            switch (choice){
                case 1:
                    tasksService.displayAssignedTasksByUserId(user.getId());
//                    workService.findByAssignee(user.getId());
                    break;
                case 2:
                    tasksService.performTheTask(sc);
                    break;
                case 3:
                    tasksService.reportWorkToBoss(sc, user, users);
                    break;
                case 4:
                    tasksService.displayTaskEvaluationsIsStaff(user.getId(), users);
                    break;
                case 999:
                    selectLoginMenu(sc,user, users );
                default:
                    System.out.println("Chức năng bạn nhập chưa được cập nhật hãy chọn chức năng khác!");
            }
        }while (true);
    }
}
