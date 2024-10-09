
import entities.Project;
import entities.User;
import entities.Work;
import services.ProjectService;
import views.LoginMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.selectDisplayMenu(sc,user ,users);
    }
}
