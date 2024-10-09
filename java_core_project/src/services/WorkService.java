package services;

import data.Database;
import entities.Project;
import entities.User;
import entities.Work;
import utils.Constant;
import utils.Untils;
import views.LoginMenu;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WorkService implements IAddDeleteUpdate {
    // thêm degssi puatusu
    ProjectService projectService = ProjectService.getInstance();
    private static WorkService workService;
    public static synchronized WorkService getInstance(){
        if (workService == null){
            workService = new WorkService();
        }
        return workService;
    }
    public Work inputWorks(Scanner sc){
        System.out.println("Tên công việc");
        String workName = sc.nextLine();
        System.out.println("Mô tả công việc");
        String workDescription = sc.nextLine();
        System.out.println("Nhập thời gian tạo công việc");
        LocalDate addDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
        System.out.println("Nhập thời gian bắt đầu làm công việc");
        LocalDate startDate = Untils.convertStringToDate(sc,"dd/MM/yyyy");
        System.out.println("Nhập thời gian kết thúc công việc");
        LocalDate endDate = Untils.convertStringToDate(sc,"dd/MM/yyyy");
        System.out.println("Nhập thái thái");
        String status = sc.nextLine();
        // Tạo đối tượng Work và trả về
        return new Work(workName, workDescription, addDate, startDate, endDate, status);
    }

    // Phương thức để thêm nhiệm vụ vào danh sách của dự án
    public void addWorkToProject(Scanner sc) {

        // Yêu cầu người dùng nhập tên dự án
        System.out.println("Nhập id dự án cần thêm công việc:");
        int id = Untils.inputInteger(sc);

        if (projectService.findPojectById(id) != null){
            // Kiểm tra xem dự án đã tồn tại trong HashMap chưa
            ArrayList<Work> works = Database.projectWorks.getOrDefault(id, new ArrayList<>());

            // Nhập thông tin nhiệm vụ
            Work work = inputWorks(sc);

            // Thêm nhiệm vụ vào danh sách
            works.add(work);

            // Cập nhật lại HashMap
            Database.projectWorks.put(id, works);

            System.out.println("công việc đã được thêm vào dự án: " + id);
        }else {
            System.out.println("không tồn tại dự án với id " + id);
        }
    }





    // TÌm kiếm công việc danh sách công việc hiện có
    /*@Override
    public void findBy(Scanner sc) {
        System.out.println("Nhập id dự án muốn xem");
        int projectId = Untils.inputInteger(sc);
        ArrayList<Work> works = Database.projectWorks.get(projectId);
        System.out.println("Danh sách nhiệm vụ của dự án ID: " + projectId);
        for (int i = 0; i < works.size(); i++) {
            System.out.println((i + 1) + ". " + works.get(i).getName());
        }
    }*/

    // xóa công việc của từng dự án theo projectId
    @Override
    public void remove(Scanner sc) {
        System.out.println("Nhập id dự án cần xóa công việc");
        int projectId= Untils.inputInteger(sc);
        // lấy công việc theo id
        ArrayList<Work> works = Database.projectWorks.get(projectId);
        if (works == null || works.isEmpty()) {
            System.out.println("Dự án này chưa có công việc nào.");
            return;
        }
        // Hiển thị danh sách công việc hiện có
        System.out.println("Danh sách công việc của dự án ID: " + projectId);
        for (int i = 0; i < works.size(); i++) {
            System.out.println((i + 1) + ". " + works.get(i).getName());
        }
        // Yêu cầu người dùng nhập số thứ tự của nhiệm vụ muốn xóa
        System.out.println("Nhập số thứ tự công việc muốn xóa:");
        int workIndex = Untils.inputInteger(sc) - 1;

        // Kiểm tra chỉ số hợp lệ
        if (workIndex < 0 || workIndex >= works.size()) {
            System.out.println("Chỉ số không hợp lệ.");
        } else {
            // Xóa nhiệm vụ
            Work removedWork = works.remove(workIndex);
            System.out.println("Đã xóa công việc: " + removedWork.getName());
            Database.projectWorks.put(projectId, works);
        }
    }

    // sữa công việc
    @Override
    public void edit(Scanner sc) {
        System.out.println("Nhập id dự án ");
        int projectId = Untils.inputInteger(sc);
        ArrayList<Work> works = Database.projectWorks.get(projectId);

        if (works == null || works.isEmpty()) {
            System.out.println("Dự án này chưa có công việc nào.");
            return;
        }

        // Hiển thị danh sách công việc hiện có
        System.out.println("Danh sách công việc của dự án ID: " + projectId);
        for (int i = 0; i < works.size(); i++) {
            System.out.println((i + 1) + ". " + works.get(i).getName());
        }

        // Yêu cầu người dùng nhập số thứ tự của công việc muốn sửa
        System.out.println("Nhập số thứ tự công việc muốn sửa:");
        int workIndex = -1;

        // Kiểm tra input cho số thứ tự
        try {
            workIndex = Untils.inputInteger(sc) - 1;

            // Kiểm tra chỉ số hợp lệ
            if (workIndex < 0 || workIndex >= works.size()) {
                System.out.println("Chỉ số không hợp lệ. Vui lòng nhập lại.");
                return; // Kết thúc hàm nếu chỉ số không hợp lệ
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi nhập số thứ tự. Vui lòng nhập lại.");
            return; // Kết thúc hàm nếu có lỗi
        }

        Work workUpdate = works.get(workIndex);

        // Sửa nhiệm vụ
        System.out.println("Hãy sửa thông tin bạn muốn chỉnh sửa:");
        System.out.println("Tên công việc");
        String workName = sc.nextLine();
        System.out.println("Mô tả công việc");
        String workDescription = sc.nextLine();
        System.out.println("Nhập thời gian tạo công việc");
        LocalDate addDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
        System.out.println("Nhập thời gian bắt đầu làm công việc");
        LocalDate startDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
        System.out.println("Nhập thời gian kết thúc công việc");
        LocalDate endDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
        System.out.println("Nhập trạng thái");
        String status = sc.nextLine();

        // Cập nhật thông tin công việc
        workUpdate.setName(workName);
        workUpdate.setWorkDescription(workDescription);
        workUpdate.setAddDate(addDate);
        workUpdate.setStartDate(startDate);
        workUpdate.setEndDate(endDate);
        workUpdate.setStatus(status);
        System.out.println("Công việc được chỉnh sửa thành công");
    }

    // hiễn thị chi tiết công việc
    @Override
    public void display(Scanner sc) {
            System.out.println("Nhập ID dự án:");
            int projectId = Untils.inputInteger(sc);

            // Kiểm tra xem dự án có tồn tại trong HashMap không
            if (Database.projectWorks.containsKey(projectId)) {
                ArrayList<Work> works = Database.projectWorks.get(projectId);

                // Nếu dự án có công việc, hiển thị danh sách công việc
                if (!works.isEmpty()) {
                    System.out.println("Danh sách công việc trong dự án " + projectId + ":");
                    for (Work work : works) {
                        /*work.setStatus("Đã giao cho " + work.getAssignee());*/
                        System.out.println("ID: " + work.getId() +
                                " | Tên công việc: " + work.getName() +
                                " | Mô tả: " + work.getWorkDescription() +
                                " | Ngày Tạo: " + work.getAddDate() +
                                " | Trạng thái: " + work.getStatus());

                    }
                } else {
                    System.out.println("Không có công việc nào trong dự án này.");
                }
            } else {
                System.out.println("Dự án với ID " + projectId + " không tồn tại.");
            }
        }


// gán công việ


    public void assignByUserId(Scanner sc, ArrayList<User> users) {
        System.out.println("Nhập ID dự án muốn phân công:");
        int projectId = Untils.inputInteger(sc);

        // Kiểm tra xem dự án có tồn tại không
        if (!Database.projectWorks.containsKey(projectId)) {
            System.out.println("Không có dự án với ID: " + projectId);
            return;
        }

        ArrayList<Work> works = Database.projectWorks.get(projectId);
        // Kiểm tra xem dự án có công việc nào không
        if (works.isEmpty()) {
            System.out.println("Không có công việc trong dự án này.");
            return;
        }

        // Hiển thị danh sách công việc
        System.out.println("Danh sách công việc của dự án ID: " + projectId);
        for (Work work : works) {
            System.out.println("ID công việc: " + work.getId() + ", Tên công việc: " + work.getName());
        }

        // Nhập ID công việc để bàn giao
        System.out.println("Nhập ID công việc bạn muốn bàn giao:");
        int workId = Untils.inputInteger(sc);
        Work selectedWork = null;

        // Tìm công việc theo ID
        for (Work work : works) {
            if (work.getId() == workId) {
                selectedWork = work; // Nếu tìm thấy, lưu công việc vào biến selectedWork
                break; // Thoát khỏi vòng lặp
            }
        }

        // Kiểm tra xem công việc có tồn tại không
        if (selectedWork == null) {
            System.out.println("Công việc bạn nhập không tồn tại. Vui lòng nhập ID công việc hợp lệ.");
            return;
        }

        // Nhập ID nhân viên để gán công việc
        System.out.println("Nhập ID cấp dưới để gán công việc:");
        int employeeId = Untils.inputInteger(sc);

        // Kiểm tra xem ID nhân viên có tồn tại không
        boolean employeeExists = false;
        for (User user : users) {
            if (user.getId() == employeeId) {
                employeeExists = true; // Đánh dấu nhân viên tồn tại
                break; // Thoát khỏi vòng lặp
            }
        }

        // Gán công việc cho nhân viên nếu tồn tại
        if (employeeExists) {
            selectedWork.setAssignee(employeeId); // Gán ID của nhân viên cho công việc
            selectedWork.setStatus("Đã bàn giao cho nhân viên ID = " + employeeId); // Cập nhật trạng thái
            System.out.println("Công việc \"" + selectedWork.getName() + "\" đã được gán cho cấp dưới ID: " + employeeId);
        } else {
            System.out.println("Không tìm thấy nhân viên với ID: " + employeeId);
        }
    }



   /* public void assignByUserId(Scanner sc, User user, ArrayList<User> users) {
        System.out.println("Nhập ID dự án muốn phân công:");
        int projectId = Untils.inputInteger(sc);

        if (Database.projectWorks.containsKey(projectId)) {
            ArrayList<Work> works = Database.projectWorks.get(projectId);

            if (works != null && !works.isEmpty()) {
                System.out.println("Danh sách công việc của dự án ID: " + projectId);
                for (int i = 0; i < works.size(); i++) {
                    System.out.println((i + 1) + ". " + works.get(i).getName());
                }

                // Nhập số thứ tự công việc để gán
                System.out.println("Nhập số thứ tự công việc bạn muốn bàn giao:");
                int workIndex = Untils.inputInteger(sc) - 1; // Giảm 1 để phù hợp với chỉ số mảng

                // Kiểm tra workIndex hợp lệ
                if (workIndex >= 0 && workIndex < works.size()) {
                    Work selectedWork = works.get(workIndex);

                    // Hiển thị danh sách trưởng phòng (ROLE_MANAGE)
                    System.out.println("Danh sách trưởng phòng:");
                    for (User u : users) {
                        if (u.getRole().equals(Constant.ROLE_MANAGE)) {
                            System.out.println("ID: " + u.getId() + " - Tên: " + u.getUsername());
                        }
                    }

                    // Nhập ID của trưởng phòng để gán công việc
                    System.out.println("Nhập ID trưởng phòng để gán công việc:");
                    int managerId = Untils.inputInteger(sc);

                    // Kiểm tra xem ID có thuộc về trưởng phòng không
                    boolean managerFound = false;
                    for (User u : users) {
                        if (u.getId() == managerId && u.getRole().equals(Constant.ROLE_MANAGE)) {
                            managerFound = true;
                            selectedWork.setAssignee(managerId); // Gán ID của trưởng phòng cho công việc
                            selectedWork.setStatus("Đã bàn giao cho trưởng phòng ID = " + managerId);
                            selectedWork.setProjectCreator("Xếp KAISHA");
                            System.out.println("Người giao: " + selectedWork.getProjectCreator() +
                                    ". Công việc \"" + selectedWork.getName() + "\" đã được gán cho trưởng phòng ID: " + managerId);
                            break;
                        }
                    }

                    if (!managerFound) {
                        System.out.println("Không tìm thấy trưởng phòng với ID: " + managerId);
                    }

                } else {
                    System.out.println("Công việc bạn nhập không tồn tại. Vui lòng chọn số thứ tự hợp lệ.");
                }

            } else {
                System.out.println("Không có công việc trong dự án này.");
            }

        } else {
            System.out.println("Không có dự án với ID: " + projectId);
        }
    }
*/



// coong ệc của tôi
    public void viewAssignedWorksByUserId(Scanner sc, User user) {
        System.out.println("Danh sách công việc đã giao cho bạn:");

        boolean hasAssignedWorks = false;
        int userId = user.getId(); // Lấy ID của nhân viên

        // Duyệt qua tất cả công việc trong Database
        for (ArrayList<Work> works : Database.projectWorks.values()) {
            for (Work work : works) {
                work.setProjectCreator("Xếp KAISHA");
                if (work.getAssignee() == userId) { // Kiểm tra xem công việc có được gán cho nhân viên không
                    System.out.println("Công việc: " + work.getName() +
                            ", Trạng thái: " + work.getStatus() +
                            ", Người giao: " + work.getProjectCreator());
                    hasAssignedWorks = true;
                }
            }
        }

        if (!hasAssignedWorks) {
            System.out.println("Bạn chưa có công việc nào được giao.");
        }
    }

    public void viewAssignedWorks(ArrayList<User> users, int userId) {
        User user = findUserById(userId, users);
        if (user != null) {
            boolean hasWorks = false;

            System.out.println("Danh sách công việc được giao cho người dùng có ID: " + userId);

            // Lặp qua tất cả các danh sách công việc trong projectWorks
            for (ArrayList<Work> works : Database.projectWorks.values()) {
                for (Work work : works) {
                    // Kiểm tra xem công việc có phải do người dùng này hay không
                    if (work.getAssignee() == userId) {
                        hasWorks = true;
                        System.out.println("Công việc: " + work.getName() + ", Mô tả: " + work.getWorkDescription() +
                                ", Người giao: " + work.getProjectCreator());
                    }
                }
            }

            if (!hasWorks) {
                System.out.println("Không có công việc nào được giao cho người dùng với ID: " + userId);
            }
        } else {
            System.out.println("Không tìm thấy người dùng với ID: " + userId);
        }
    }








   /* public void assignByUserId(Scanner sc,User user, ArrayList<User> users) {
        System.out.println("Nhập ID dự án muốn phân công:");
        int projectId = Untils.inputInteger(sc);

        if (Database.projectWorks.containsKey(projectId)) {
            ArrayList<Work> works = Database.projectWorks.get(projectId);

            if (works != null && !works.isEmpty()) {
                System.out.println("Danh sách công việc của dự án ID: " + projectId);
                for (int i = 0; i < works.size(); i++) {
                    System.out.println((i + 1) + ". " + works.get(i).getName());
                }
                // Nhập số thứ tự công việc để gán
                    System.out.println("Nhập số thứ tự công việc bạn muốn bàn giao");
                     int workIndex = Untils.inputInteger(sc) - 1; // Giảm 1 để phù hợp với chỉ số mảng
                    // Kiểm tra workIndex hợp lệ
                    if (workIndex >= 0 && workIndex < works.size()) {
                        Work selectedWork = works.get(workIndex);
                        UserServices userServices = new UserServices();
                        userServices.displayRegisters(users);
                        // Nhập ID người dùng để gán
                        System.out.println("Nhập ID người dùng để gán công việc:");
                        int userId = Untils.inputInteger(sc);
                        if (userId == 1) {
                            System.out.println("t là sếp ");
                        } else if (userId > 0 && userId <= users.size()) {
                            selectedWork.setAssignee(userId);
                            selectedWork.setStatus("đã bàn giao cho nhân viên có id = " + userId);
                            selectedWork.setProjectCreator("Xếp KAISHA");
                            System.out.println("Người giao" + selectedWork.getProjectCreator() + "Công việc \"" + selectedWork.getName() + "\" đã được gán cho người dùng ID: " + userId);
                        } else {
                            System.out.println("không tim thấy thành vie với id " + userId);
                        }
                    } else {
                        System.out.println("Công việc bạn nhập không tồn tại. Vui lòng chọn số thứ tự hợp lệ.");
                    }
                }else {
                System.out.println("không có công việc");
            }
            } else {
                System.out.println("Không có dự án");
            }
        }*/

















    // lịch sử phân công
    public void viewAssignmentHistory() {
        System.out.println("Danh sách công việc đã giao:");

        // Kiểm tra xem có công việc nào đã được giao không
        boolean assignmentFound = false;

        // Lặp qua tất cả các dự án và công việc
        for (ArrayList<Work> works : Database.projectWorks.values()) {
            for (Work work : works) {
                if (work.getAssignee() != 0) { // Kiểm tra công việc đã được gán cho nhân viên
                    assignmentFound = true;
                    System.out.println("Công việc: \"" + work.getName() +
                            "\" | ID Nhân viên: " + work.getAssignee() +
                            " | Ngày giao: " + work.getStartDate());
                }
            }
        }

        if (!assignmentFound) {
            System.out.println("Không có công việc nào đã được giao.");
        }
    }


    // hiễn thị công việc lại trạng thái

/*    public void displayWorksStatus(ArrayList<Work> works) {
        if (works == null || works.isEmpty()) {
            System.out.println("Không có công việc nào để hiển thị.");
            return;
        }

        System.out.println("Danh sách công việc và trạng thái:");
        for (int i = 0; i < works.size(); i++) {
            Work work = works.get(i);
            System.out.println((i + 1) + ". " + work.getWorkName() + " - Trạng thái: " + work.getStatus());
        }
    }*/


    // danh sách các công việc thuộc projectId
    /*public void findProjectsById(int projectId){
        ArrayList<Work> works = new ArrayList<>();
        for (Work work: Database.works) {
            if (work.getProjectId() == projectId){
                works.add(work);
            }
        }
        System.out.println(works);
    }


    public Project findProjectByProjectNameAndProjectId(int projectId, String projectName) {
        for (Project project : Database.projects) {
            // So sánh ID và tên dự án không phân biệt chữ hoa, chữ thường
            if (project.getId() == projectId && project.getProjectName().equalsIgnoreCase(projectName)) {
                return project;
            }
        }
        return null;
    }*/

   /* public void displayWorksById(Scanner sc){
        System.out.println("Nhập id dự án");
        int projectId = Untils.inputInteger(sc);
        System.out.println("Nhập id Công việc của dự án");
        int workId = Untils.inputInteger(sc);
        Work work = findWorksByWordId(workId);
        work.setProjectId(projectId);
        diplayWorkInfo(work);
    }*/


    // chức nắng số 5 : quản lý thành viên
    public void appointManagerById(Scanner sc, ArrayList<User> users) {
        System.out.println("Nhập ID của nhân viên muốn bổ nhiệm làm trưởng phòng: ");
        int userId = Untils.inputInteger(sc);

        // Tìm kiếm nhân viên theo userId
        User staff = findUserById(userId, users);
        if (staff != null && staff.getRole().equals(Constant.ROLE_STAFF)) {
            // Thay đổi vai trò thành trưởng phòng
            staff.setRole(Constant.ROLE_MANAGE);
            System.out.println("Đã bổ nhiệm nhân viên có ID " + userId + " làm trưởng phòng.");
        } else {
            System.out.println("Không tìm thấy nhân viên hoặc nhân viên này không phải là nhân viên.");
        }
    }

    public void demoteManagerById(Scanner sc, ArrayList<User> users) {
        System.out.println("Nhập ID của trưởng phòng muốn giáng chức xuống nhân viên: ");
        int userId = Untils.inputInteger(sc);
        // Tìm kiếm trưởng phòng theo userId
        User manager = findUserById(userId, users);
        if (manager != null && manager.getRole().equals(Constant.ROLE_MANAGE)) {
            // Thay đổi vai trò thành nhân viên
            manager.setRole(Constant.ROLE_STAFF);
            System.out.println("Đã giáng chức trưởng phòng có ID " + userId + " xuống nhân viên.");
        } else {
            System.out.println("Không tìm thấy trưởng phòng hoặc người này không phải là trưởng phòng.");
        }
    }

    // Hàm hỗ trợ tìm kiếm người dùng theo ID
    public User findUserById(int userId, ArrayList<User> users) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    // hiễn thị tất cả nhân viên trưởng phòng
    public void displayStaffAndManagers(ArrayList<User> users) {
        System.out.println("--- Danh sách Nhân viên và Trưởng phòng ---");
        for (User user : users) {
            if (user.getRole().equals(Constant.ROLE_STAFF)) {
                System.out.println("Nhân viên: " + " ID :" + user.getId() + ", Tên:" +user.getUsername() + ", Email:" + user.getEmail() + ", Role: " + user.getRole());
            } else if (user.getRole().equals(Constant.ROLE_MANAGE)) {
                System.out.println("Trưởng phòng: " + " ID :" + user.getId() + ", Tên:" +user.getUsername() + ", Email:" + user.getEmail() + ", Role: " + user.getRole());
            }
        }
    }




    public void findAndDisplayWorkByIdInProject(Scanner sc) {
        System.out.println("Nhập ID dự án:");
        int projectId = Untils.inputInteger(sc); // Nhập ID dự án từ người dùng

        System.out.println("Nhập ID công việc bạn muốn tìm kiếm:");
        int workId = Untils.inputInteger(sc); // Nhập ID công việc từ người dùng

        Work work = findWorkByIdInProject(projectId, workId); // Tìm công việc theo ID dự án và ID công việc

        if (work != null) {
            // Nếu tìm thấy công việc, in thông tin của nó
            System.out.println("Thông tin công việc:");
            work.displayWork(); // Giả sử bạn đã có phương thức displayWork() trong lớp Work
        } else {
            // Nếu không tìm thấy công việc, thông báo cho người dùng
            System.out.println("Không tìm thấy công việc với ID: " + workId + " trong dự án ID: " + projectId);
        }
    }


    public Work findWorkByIdInProject(int projectId, int workId) {
        ArrayList<Work> works = Database.projectWorks.get(projectId); // Lấy danh sách công việc theo ID dự án
        if (works != null) {
            for (Work work : works) {
                if (work.getId() == workId) {
                    return work; // Trả về công việc nếu tìm thấy
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy công việc
    }
    public void findWorksByNameInProject(Scanner sc) {
        System.out.println("Nhập ID dự án:");
        int projectId = Untils.inputInteger(sc); // Nhập ID dự án từ người dùng

        // Lấy danh sách công việc theo ID dự án
        ArrayList<Work> works = Database.projectWorks.get(projectId);
        if (works == null || works.isEmpty()) {
            System.out.println("Dự án này chưa có công việc nào.");
            return;
        }

        System.out.println("Nhập tên công việc bạn muốn tìm kiếm:");
        String searchKeyword = sc.nextLine(); // Nhập từ khóa tìm kiếm

        boolean found = false;
        for (Work work : works) {
            if (work.getName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                // Nếu tên công việc chứa từ khóa tìm kiếm, in thông tin công việc
                System.out.println("Công việc tìm thấy: ");
                work.displayWork(); // Giả sử bạn đã có phương thức displayWork() trong lớp Work
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy công việc nào với tên chứa: " + searchKeyword + " trong dự án ID: " + projectId);
        }
    }







  /*  public void viewAssignedWorks(Scanner sc, ArrayList<User> users) {
        System.out.println("Nhập ID của bạn để xem công việc ");
        int userId = Untils.inputInteger(sc);
        User user = findUserById(userId, users);
        if (user != null && user.getId() == userId){
            boolean hasWorks = false;

            System.out.println("Danh sách công việc được giao cho người dùng có ID: " + userId);

            // Lặp qua tất cả các danh sách công việc trong projectWorks
            for (ArrayList<Work> works : Database.projectWorks.values()) {
                for (Work work : works) {
                    // Kiểm tra xem công việc có phải do người dùng này hay không
                    if (work.getAssignee() == userId) {
                        hasWorks = true;
                        System.out.println("Công việc: " + work.getName() + ", Mô tả: " + work.getWorkDescription() +
                                 ", Người giao" + work.getProjectCreator());
                    }
                }
            }
            if (!hasWorks) {
                System.out.println("Không có công việc nào được giao cho người dùng với ID: " + userId);
            }
        }

    }*/


    // xem công việc của tooi role manager
    public void findByAssignee(int assigneeId) {
        System.out.println("Danh sách công việc được gán cho nhân viên với ID: " + assigneeId);
        boolean found = false;

        // Duyệt qua tất cả các dự án trong Database.projectWorks
        for (ArrayList<Work> workList : Database.projectWorks.values()) {
            for (Work work : workList) {
                work.setProjectCreator("Xếp KAISHA");
                // Kiểm tra xem công việc có gán cho nhân viên với assigneeId không
                if (work.getAssignee() == assigneeId) {
                    System.out.println(" ID công việc: " + work.getId() +
                            " | Tên công việc: " + work.getName() +
                            " | Mô tả: " + work.getWorkDescription() +
                            " | Người giao: " + work.getProjectCreator() +
                            " | Thời hạn nộp công việc: " + work.getEndDate());
                    found = true; // Đánh dấu là đã tìm thấy công việc
                }
            }
        }

        // Nếu không tìm thấy công việc nào
        if (!found) {
            System.out.println("Không có công việc nào được gán cho nhân viên với ID: " + assigneeId);
        }
    }



    //  sếp thông báo
    public void notification(Scanner sc, ArrayList<User> users){
            System.out.println("Nhập ID dự án muốn thong báo:");
            int projectId = Untils.inputInteger(sc);

            // Kiểm tra xem dự án có tồn tại không
            if (!Database.projectWorks.containsKey(projectId)) {
                System.out.println("Không có dự án với ID: " + projectId);
                return;
            }

            ArrayList<Work> works = Database.projectWorks.get(projectId);
            // Kiểm tra xem dự án có công việc nào không
            if (works.isEmpty()) {
                System.out.println("Không có công việc trong dự án này.");
                return;
            }

            // Hiển thị danh sách công việc
            System.out.println("Danh sách công việc của dự án ID: " + projectId);
            for (Work work : works) {
                System.out.println("ID công việc: " + work.getId() + ", Tên công việc: " + work.getName());
            }

            // Nhập ID công việc để bàn giao
            System.out.println("Nhập ID công việc bạn muốn thông báo:");
            int workId = Untils.inputInteger(sc);
            Work selectedWork = null;

            // Tìm công việc theo ID
            for (Work work : works) {
                if (work.getId() == workId) {
                    selectedWork = work; // Nếu tìm thấy, lưu công việc vào biến selectedWork
                    break; // Thoát khỏi vòng lặp
                }
            }

            // Kiểm tra xem công việc có tồn tại không
            if (selectedWork == null) {
                System.out.println("Công việc bạn nhập không tồn tại. Vui lòng nhập ID công việc hợp lệ.");
                return;
            }

            // Nhập ID nhân viên để gán công việc
            System.out.println("Nhập ID nhân viên để gán công việc:");
            int employeeId = Untils.inputInteger(sc);

            // Kiểm tra xem ID nhân viên có tồn tại không
            boolean employeeExists = false;
            for (User user : users) {
                if (user.getId() == employeeId) {
                    employeeExists = true; // Đánh dấu nhân viên tồn tại
                    break; // Thoát khỏi vòng lặp
                }
            }

            System.out.println("Nhập thông báo ");
            String notification = sc.nextLine();

            // Gán công việc cho nhân viên nếu tồn tại
            if (employeeExists) {
                selectedWork.setAssignee(employeeId); // Gán ID của nhân viên cho công việc
                selectedWork.setStatus("Đã gửi giao cho nhân viên ID = " + employeeId); // Cập nhật trạng thái
                selectedWork.setNotification(notification); // **Cập nhật thông báo cho công việc**
            } else {
                System.out.println("Không tìm thấy nhân viên với ID: " + employeeId);
            }
        }

    public void commentToBoss(Scanner sc, ArrayList<User> users){
        System.out.println("Nhập ID dự án muốn đã hoàn thành:");
        int projectId = Untils.inputInteger(sc);

        // Kiểm tra xem dự án có tồn tại không
        if (!Database.projectWorks.containsKey(projectId)) {
            System.out.println("Không có dự án với ID: " + projectId);
            return;
        }

        ArrayList<Work> works = Database.projectWorks.get(projectId);
        // Kiểm tra xem dự án có công việc nào không
        if (works.isEmpty()) {
            System.out.println("Không có công việc trong dự án này.");
            return;
        }

        // Hiển thị danh sách công việc
        System.out.println("Danh sách công việc của dự án ID: " + projectId);
        for (Work work : works) {
            System.out.println("ID công việc: " + work.getId() + ", Tên công việc: " + work.getName());
        }

        // Nhập ID công việc để bàn giao
        System.out.println("Nhập ID công việc để đánh giá khách quan:");
        int workId = Untils.inputInteger(sc);
        Work selectedWork = null;

        // Tìm công việc theo ID
        for (Work work : works) {
            if (work.getId() == workId) {
                selectedWork = work; // Nếu tìm thấy, lưu công việc vào biến selectedWork
                break; // Thoát khỏi vòng lặp
            }
        }

        // Kiểm tra xem công việc có tồn tại không
        if (selectedWork == null) {
            System.out.println("Công việc bạn nhập không tồn tại. Vui lòng nhập ID công việc hợp lệ.");
            return;
        }

        // Nhập ID nhân viên để gán công việc
        System.out.println("Nhập ID cấp dưới để đánh giá :");
        int employeeId = Untils.inputInteger(sc);

        // Kiểm tra xem ID nhân viên có tồn tại không
        boolean employeeExists = false;
        for (User user : users) {
            if (user.getId() == employeeId) {
                employeeExists = true; // Đánh dấu nhân viên tồn tại
                break; // Thoát khỏi vòng lặp
            }
        }

        System.out.println("Nhập đánh giá ");
        String notification = sc.nextLine();

        // Gán công việc cho nhân viên nếu tồn tại
        if (employeeExists) {
            selectedWork.setAssignee(employeeId); // Gán ID của nhân viên cho công việc
            selectedWork.setStatus("Đã gửi đánh giá cho thành viên ID = " + employeeId); // Cập nhật trạng thái
            selectedWork.setNotification(notification); // **Cập nhật thông báo cho công việc**
        } else {
            System.out.println("Không tìm thấy nhân viên với ID: " + employeeId);
        }
    }
    public void viewNotifications(int employeeId) {
        // Duyệt qua tất cả các dự án và công việc liên quan để tìm các công việc được giao cho nhân viên này
        boolean hasNotifications = false;

        for (Integer projectId : Database.projectWorks.keySet()) {
            ArrayList<Work> works = Database.projectWorks.get(projectId);

            for (Work work : works) {
                // Kiểm tra nếu công việc được giao cho nhân viên có ID là employeeId
                if (work.getAssignee() == employeeId) {
                    hasNotifications = true;
                    System.out.println("Công việc ID: " + work.getId() + ", Tên công việc: " + work.getName());
                    System.out.println("Thông báo từ sếp KAISHA: " + work.getNotification());
                }
            }
        }

        if (!hasNotifications) {
            System.out.println("Không có thông báo nào cho nhân viên ID: " + employeeId);
        }
    }

    public void reportWorkToBoss(Scanner sc, User manager, ArrayList<User> users) {
        User boss = null;

        // Tìm kiếm sếp trong danh sách người dùng
        for (User user : users) {
            if (user.getRole().equals(Constant.ROLE_BOSS)) {
                boss = user; // Lưu trữ sếp tìm thấy
                break; // Chỉ cần tìm sếp đầu tiên
            }
        }

        // Kiểm tra xem sếp có tồn tại không
        if (boss != null) {
            System.out.println("Sếp tìm thấy: " + boss.getUsername() + " (ID: " + boss.getId() + ")");
            System.out.print("Nhập nội dung báo cáo công việc: ");
            String reportContent = sc.nextLine(); // Nhập nội dung báo cáo

            // Thêm thông tin người gửi vào báo cáo
            String reportWithSender = "Báo cáo từ " + manager.getUsername() + " (ID: " + manager.getId() + "): " + reportContent;

            // Kiểm tra xem danh sách thông báo của sếp đã được khởi tạo chưa
            if (boss.getNotifications() == null) {
                boss.setNotifications(new ArrayList<>()); // Khởi tạo danh sách thông báo nếu chưa có
            }

            // Thêm báo cáo vào danh sách thông báo của sếp
            boss.getNotifications().add(reportWithSender);

            System.out.println("Báo cáo công việc đã được gửi đến sếp ID: " + boss.getId());
        } else {
            System.out.println("Không tìm thấy sếp trong danh sách người dùng.");
        }
    }



    public void displayNotifications(User user) {
        // Kiểm tra xem danh sách thông báo có tồn tại không
        if (user.getNotifications() != null && !user.getNotifications().isEmpty()) {
            System.out.println("Danh sách thông báo:");

            for (String notification : user.getNotifications()) {
                System.out.println("- " + notification); // Hiển thị từng thông báo
            }
        } else {
            System.out.println("Không có thông báo nào cho " + user.getUsername());
        }
    }


}


