package entities;

import java.time.LocalDate;

public class Tasks {
   private static int autoId = 0;
   private int id;
   private String taskName;
   private String taskDescription;
    private LocalDate addDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private int workId;
    private int assignee;
    private String workCreator; // người tọa công việc


    public Tasks() {
    }

    public Tasks( String taskName, String taskDescription, LocalDate addDate, LocalDate startDate, LocalDate endDate, String status) {
        this.id = ++autoId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.addDate = addDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public String getWorkCreator() {
        return workCreator;
    }

    public void setWorkCreator(String workCreator) {
        this.workCreator = workCreator;
    }


    public void displayTask() {
        System.out.println("Work Details:");
        System.out.println("---------------------");
        System.out.println("ID                : " + id);
        System.out.println("Work Name         : " + taskName);
        System.out.println("Description       : " + taskDescription);
        System.out.println("Add Date          : " + addDate);
        System.out.println("Start Date        : " + startDate);
        System.out.println("End Date          : " + endDate);
        System.out.println("Status            : " + status);
        System.out.println("---------------------");
    }
}
