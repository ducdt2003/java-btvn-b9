package data;

import entities.Project;
import entities.Tasks;
import entities.User;
import entities.Work;

import java.util.ArrayList;
import java.util.HashMap;


public class Database {
    public static ArrayList<Project> projects = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Work> works = new ArrayList<>();
    public static ArrayList<Tasks> tasks = new ArrayList<>();
    // HashMap để lưu nhiệm vụ theo projectId
    public static HashMap<Integer, ArrayList<Work>> projectWorks = new HashMap<>();
}
