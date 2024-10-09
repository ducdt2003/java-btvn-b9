package entities;

import java.time.LocalDate;

public class Project {
    private static int autoId = 0;
    private int id;
    private String projectName;
    private String projectDescribe;
    private LocalDate addDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

   private int workId;
    public Project() {
    }

    public Project(String projectName, String projectDescribe, LocalDate addDate, LocalDate startDate, LocalDate endDate, String status) {
        this.id = ++autoId;
        this.projectName = projectName;
        this.projectDescribe = projectDescribe;
        this.addDate = addDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Project.autoId = autoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getendDate() {
        return endDate;
    }

    public void setendDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public void dislayProject() {
        System.out.println("Project Details:");
        System.out.println("---------------------");
        System.out.println("ID             : " + getId());
        System.out.println("Project Name   : " + getProjectName());
        System.out.println("Description    : " + getProjectDescribe());
        System.out.println("Add Date       : " + getAddDate());
        System.out.println("Start Date     : " + getStartDate());
        System.out.println("End Date       : " + getendDate());
        System.out.println("Status         : " + getStatus());
    }
}
