package services;

import data.Database;
import entities.Project;
import entities.Work;
import utils.Untils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;

public class ProjectService implements IAddDeleteUpdate {
    private static ProjectService projectService;
    public static synchronized ProjectService getInstance(){
        if (projectService == null){
            projectService = new ProjectService();
        }
        return projectService;
    }

    // tạo dự án tính năng 1 của chưc năng 1 boss
    public Project inputProject(Scanner sc){
        System.out.println("Nhập tên dự án");
        String projectName = sc.nextLine();
        System.out.println("Nhập mô tả dự án");
        String projectDescribe = sc.nextLine();
        System.out.println("Nhập thời gian tạo dự án");
        LocalDate addDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
        System.out.println("Nhập thời gian bắt đầu làm dự án");
        LocalDate startDate = Untils.convertStringToDate(sc,"dd/MM/yyyy");
        System.out.println("Nhập thời gian kết thúc dự án");
        LocalDate endDate = Untils.convertStringToDate(sc,"dd/MM/yyyy");
        System.out.println("Nhập trạng thái của dự án");
        String status = sc.nextLine();
        Project project = new Project(projectName,projectDescribe,addDate,startDate,endDate,status);
        Database.projects.add(project);
        return project;
    }

    // xem dự án đã tạo
    public void viewHistoryProject(){
        for (Project project: Database.projects) {
            System.out.println("ID dự án: " + project.getId() + " | Tên dự án: " + project.getProjectName() + " | Ngày tạo dự án: "
            + project.getAddDate());
        }
    }
    // iterface xem các dự án theo id tính năng 2 chức năng 1 của boss
/*    @Override
    public void findBy(Scanner sc) {
        System.out.println("Nhập id dự án cần xem");
        int projectId = Untils.inputInteger(sc);
        System.out.println("Nhập tên dự án cần xem");
        String projectName = sc.nextLine();
        Project projectFine = findProjectByProjectNameAndProjectId(projectId,projectName);
        if ( projectFine != null){
            System.out.println("Dự án bạn tìm kiếm");
            projectFine.dislayProject();
        }else {
            System.out.println("dự án không tồn tại");
        }
    }*/

    // xóa dự án theo id và tên tính năng 2 của chưc năng 1 boss
    @Override
    public void remove(Scanner sc) {
        System.out.println("Nhập id dự án ");
        int projectId = Untils.inputInteger(sc);
        System.out.println("Nhập tên dự án");
        String projectName = sc.nextLine();
        Project projectFine = findProjectByProjectNameAndProjectId(projectId,projectName);
        if (projectFine != null){
            Database.projects.remove(projectFine);
            System.out.println("xóa thành công dự án " + projectName);
        }else {
            System.out.println("dự án không tồn tại");
        }
    }

    // cập nhật chỉnh sữa thng tin dự án
    @Override
    public void edit(Scanner sc) {
        System.out.println("Nhập id dự án ");
        int projectId = Untils.inputInteger(sc);
        System.out.println("Nhập tên dự án ");
        String projectName = sc.nextLine();
        Project projectFine = findProjectByProjectNameAndProjectId(projectId,projectName);
        if (projectFine != null){
            System.out.println("Nhập tên dự án mới (hiện tại: " + projectFine.getProjectName() + "):");
            String newProjectName = sc.nextLine();
            System.out.println("Nhập mô tả dự án mới (hiện tại: " + projectFine.getProjectDescribe() + "):");
            String newProjectDescribe = sc.nextLine();
            System.out.println("Nhập thời gian khởi tạo dự (hiện tại" + projectFine.getAddDate() + "):");
            LocalDate nerAddDate = Untils.convertStringToDate(sc,"dd/MM/yyyy");
            System.out.println("Nhập thời gian bắt đầu mới (hiện tại: " + projectFine.getStartDate() + "):");
            LocalDate newStartDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
            System.out.println("Nhập thời gian kết thúc mới (hiện tại: " + projectFine.getendDate() + "):");
            LocalDate newEndDate = Untils.convertStringToDate(sc, "dd/MM/yyyy");
            System.out.println("Nhập trạng thái mới (hiện tại: " + projectFine.getStatus() + "):");
            String newStatus = sc.nextLine();
            projectFine.setProjectName(newProjectName);
            projectFine.setProjectDescribe(newProjectDescribe);
            projectFine.setAddDate(nerAddDate);
            projectFine.setStartDate(newStartDate);
            projectFine.setendDate(newEndDate);
            projectFine.setStatus(newStatus);
        }else {
            System.out.println("dự án không tồn tại");
        }
    }

    @Override
    public void display(Scanner sc) {
        for (Project project : Database.projects) {
            project.dislayProject();
        }
    }

    public Project findProjectByProjectNameAndProjectId(int projectId, String projectName) {
        for (Project project : Database.projects) {
            // So sánh ID và tên dự án không phân biệt chữ hoa, chữ thường
            if (project.getId() == projectId && project.getProjectName().equalsIgnoreCase(projectName)) {
                return project;
            }
        }
        return null;
    }

    // hiễn thị dự án theo id
    public void displayFindByProjecId(Scanner sc){
        System.out.println("Nhập id dự án");
        int projectId = Untils.inputInteger(sc);
        Project project = findPojectById(projectId);
        if (project != null && project.getId() == projectId){
            project.dislayProject();
        }else {
            System.out.println("không tìm thấy dự án với id " + projectId);
        }
    }


    public Project findPojectById(int id){
        for (Project project: Database.projects) {
            if (project.getId() == id){
                return project;
            }
        }
        return null;
    }


    // tìm kiếm theo tên dự án

    public void findByProjectName(Scanner sc){
        System.out.println("Nhập tên dự án bạn muốn tìm kiếm");
        String searchKeyword = sc.nextLine();
        searchProjectByName(searchKeyword);
    }
    public static void searchProjectByName(String name) {
        boolean found = false;
        for (Project project : Database.projects) {
            if (project.getProjectName().toLowerCase().contains(name.toLowerCase())) {
                project.dislayProject();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy dự án với tên: " + name);
        }
    }







    // xem các dự án đã được tạo tính năng 5 của chưc năng 1 boss




}
